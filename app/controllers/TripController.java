package controllers;

import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Int;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TripController extends Controller
{
    private FormFactory formFactory;
    private JPAApi jpaApi;

    @Inject
    public TripController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getTripInformation()
    {
        String userId = session().get("userId");
        return ok(views.html.tripinformation.render("" + userId));
    }

    @Transactional
    public Result postTripInformation()
    {
        DynamicForm form = formFactory.form().bindFromRequest(); // Play framework: FormFactory for HTML interaction

        String userId = session().get("userId");    // Gets the user ID from the current session

        ReqTrip newTrip = new ReqTrip(); // Creates a requested trip profile
        String budgetText = form.get("budget"); // Gets the users budget
        BigDecimal budget = new BigDecimal(budgetText); // Converts the user budget
        LocalDate startDate = LocalDate.parse(form.get("startdate"));  // Requested start date
        LocalDate endDate = LocalDate.parse(form.get("enddate"));   // Requested end date
        int numberOfTravelers = Integer.parseInt(form.get("numberoftravelers")); // Requested Traveler count
        String pets = form.get("pets");     // pets flag
        String tripPurpose = form.get("purpose");
        String nonNegotiable = form.get("nonnegotiable");
        String notes = form.get("notes");

        Logger.debug("userId" + userId);

            newTrip.setUserId(Integer.parseInt(userId));
            newTrip.setBudget(budget);
            newTrip.setStartDate(startDate);
            newTrip.setEndDate(endDate);
            newTrip.setNumberOfTravelers(numberOfTravelers);
            newTrip.setPets(pets);
            newTrip.setTripPurpose(tripPurpose);
            newTrip.setNonNegotiable(nonNegotiable);
            newTrip.setNotes(notes);
            jpaApi.em().persist(newTrip);

        Destination newDestination = new Destination();
        String destinationName = form.get("destinationchoice");

            newDestination.setDestinationName(destinationName);
            jpaApi.em().persist(newDestination);

        int reqTripId = newTrip.getReqTripId();
        int destinationId = newDestination.getDestinationId();

        session().put("reqTripId", "" + reqTripId);
        session().put("destinationId", "" + destinationId);

        ReqTripDestination newReqDestination = new ReqTripDestination();

            newReqDestination.setReqTripId(reqTripId);
            newReqDestination.setDestinationId(destinationId);
            jpaApi.em().persist(newReqDestination);


        String transportation = form.get("transportation");

        if(transportation.equals("personalcar"))
        {
            transportation = "Personal Car";
        }

        if(transportation.equals("rentalcar"))
        {
            transportation = "Rental Car";
        }

        String transportationSQL = "SELECT t FROM TransportationType t WHERE transportationTypeName LIKE :transportation";
        TransportationType transportationType = jpaApi.em().createQuery(transportationSQL, TransportationType.class).setParameter("transportation", transportation).getSingleResult();

        ReqTripTransportation newReqTransportation = new ReqTripTransportation();

        newReqTransportation.setReqTripId(reqTripId);
        newReqTransportation.setTransportationTypeId(transportationType.getTransportationTypeId());
        jpaApi.em().persist(newReqTransportation);

        String accommodationType = form.get("accommodation");

        if (accommodationType.equals("vacationhome"))
        {
            accommodationType = "Vacation Home";
        }

        if (accommodationType.equals("bedandbreakfast"))
        {
            accommodationType = "Bed and Breakfast";
        }

        String accommodationSQL = "SELECT a FROM AccommodationType a WHERE accommodationTypeName LIKE :accommodationType";
        AccommodationType newAccommodationType = jpaApi.em().createQuery(accommodationSQL, AccommodationType.class).setParameter("accommodationType", accommodationType).getSingleResult();

        ReqTripAccommodation newReqAccommodation = new ReqTripAccommodation();

            newReqAccommodation.setReqTripId(reqTripId);
            newReqAccommodation.setAccommodationTypeId(newAccommodationType.getAccommodationTypeId());
            jpaApi.em().persist(newReqAccommodation);

        Map<String, String[]> activityOptions = request().body().asFormUrlEncoded();
        String[] activities = activityOptions.get("activity[]");

        if (activities != null)
        {
            for (String reqTripActivityId:activities)
            {
                ReqTripActivity newReqTripActivity = new ReqTripActivity();
                newReqTripActivity.setReqTripId(reqTripId);
                newReqTripActivity.setActivityTypeId(Integer.parseInt(reqTripActivityId));
                jpaApi.em().persist(newReqTripActivity);
            }
        }

        Map<String, String[]> diningOptions = request().body().asFormUrlEncoded();
        String[] dining = diningOptions.get("dining[]");

        if (dining != null)
        {
            for (String reqTripDiningId:dining)
            {
                ReqTripDining newReqTripDining = new ReqTripDining();
                newReqTripDining.setReqTripId(reqTripId);
                newReqTripDining.setDiningTypeId(Integer.parseInt(reqTripDiningId));
                jpaApi.em().persist(newReqTripDining);
            }
        }

        return redirect("/userprofile");
    }

    @Transactional
    public Result getExistingTrips()
    {
        String userIdText = session().get("userId");
        int userId = Integer.parseInt(userIdText);
        Logger.debug("userId" + userId);

        String reqTripSQL = "SELECT NEW models.ExistingTrip(rx.userId, r.reqTripId, d.destinationName, rx.startDate, rx.endDate, rx.tripPurpose) " +
                            "FROM ReqTripDestination r " +
                            "JOIN Destination d ON r.destinationId = d.destinationId " +
                            "JOIN ReqTrip rx ON r.reqTripId = rx.reqTripId " +
                            "JOIN User u ON u.userId = rx.userId " +
                            "WHERE rx.userId = :userId";

        List<ExistingTrip> existingTrips = jpaApi.em().createQuery(reqTripSQL, ExistingTrip.class).setParameter("userId", userId).getResultList();

        return ok(views.html.existingtrips.render("" + userId, existingTrips));
    }
}
