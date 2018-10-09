package controllers;

import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultantController extends Controller
{
    private FormFactory formFactory;
    private JPAApi jpaApi;

    @Inject
    public ConsultantController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    @Transactional
    public Result getConsultantDashboard() throws InterruptedException, ApiException, IOException
    {
        String sql = "SELECT NEW models.SubmittedTrip (r.reqTripId, r.userId, u.firstName, u.lastName, r.startDate, r.endDate, r.tripPurpose) " +
                "FROM ReqTrip r " +
                "JOIN User u ON r.userId = u.userId ";

        List<SubmittedTrip> submittedTrips = jpaApi.em().createQuery(sql, SubmittedTrip.class).getResultList();

        return ok(views.html.consultantdashboard.render(submittedTrips));
    }

    @Transactional
    public Result postConsultantDashboard()
    {
        return ok();
    }

    @Transactional
    public Result getTrip(int reqTripId) throws InterruptedException, ApiException, IOException
    {
        String userSQL = "SELECT NEW models.UserReqTrip (r.reqTripId, u.userId, u.firstName, u.lastName, u.emailAddress, u.zipCode, u.restriction) " +
                "FROM User u " +
                "LEFT OUTER JOIN ReqTrip r ON u.userId = r.userId " +
                "WHERE r.reqTripId = :reqTripId";
        List<UserReqTrip> userReqTrips = jpaApi.em().createQuery(userSQL, UserReqTrip.class).setParameter("reqTripId", reqTripId).getResultList();

        String tripSQL = "SELECT r FROM ReqTrip r WHERE reqTripId = :reqTripId";
        List<ReqTrip> trips = jpaApi.em().createQuery(tripSQL, ReqTrip.class).setParameter("reqTripId", reqTripId).getResultList();

        String destinationSQL = "SELECT NEW models.UserDestination (d.destinationId, d.destinationName) " +
                                "FROM Destination d " +
                                "JOIN ReqTripDestination r on d.destinationId = r.destinationId " +
                                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                                "WHERE rt.reqTripId = :reqTripId ";
        List<UserDestination> userDestinations = jpaApi.em().createQuery(destinationSQL, UserDestination.class).setParameter("reqTripId", reqTripId).getResultList();

        String accommodationSQL = "SELECT NEW models.UserAccommodation (a.accommodationTypeId, a.accommodationTypeName) " +
                "FROM AccommodationType a " +
                "JOIN ReqTripAccommodation r ON a.accommodationTypeId = r.accommodationTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserAccommodation> userAccommodations = jpaApi.em().createQuery(accommodationSQL, UserAccommodation.class).setParameter("reqTripId", reqTripId).getResultList();

        String activitySQL = "SELECT NEW models.UserActivity (a.activityTypeName) " +
                "FROM ActivityType a " +
                "JOIN ReqTripActivity r ON a.activityTypeId = r.activityTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserActivity> userActivities = jpaApi.em().createQuery(activitySQL, UserActivity.class).setParameter("reqTripId", reqTripId).getResultList();

        String diningSQL = "SELECT NEW models.UserDining (d.diningTypeId, d.diningTypeName) " +
                "FROM DiningType d " +
                "JOIN ReqTripDining r ON d.diningTypeId = r.diningTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserDining> userDinings = jpaApi.em().createQuery(diningSQL, UserDining.class).setParameter("reqTripId", reqTripId).getResultList();

        String transportationSQL = "SELECT NEW models.UserTransportation (t.transportationTypeId, t.transportationTypeName) " +
                "FROM TransportationType t " +
                "JOIN ReqTripTransportation r ON t.transportationTypeId = r.transportationTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserTransportation> userTransportations = jpaApi.em().createQuery(transportationSQL, UserTransportation.class).setParameter("reqTripId", reqTripId).getResultList();

        session().put("destination", "" + userDestinations);

        PlacesSearchResult[] places = Place.getPlace("kensington, london, rentals");


//        for()
//        {
//            PlaceDetails details = Place.getPlaceDetails()places[i].placeId);
//
//        }
        return ok(views.html.trip.render(userReqTrips, trips, userDestinations, userAccommodations, userActivities, userDinings, userTransportations, places));
    }

    @Transactional
    public Result postTrip(int reqTripId) throws InterruptedException, ApiException, IOException
    {
        String userSQL = "SELECT NEW models.UserReqTrip (r.reqTripId, u.userId, u.firstName, u.lastName, u.emailAddress, u.zipCode, u.restriction) " +
                "FROM User u " +
                "LEFT OUTER JOIN ReqTrip r ON u.userId = r.userId " +
                "WHERE r.reqTripId = :reqTripId";
        List<UserReqTrip> userReqTrips = jpaApi.em().createQuery(userSQL, UserReqTrip.class).setParameter("reqTripId", reqTripId).getResultList();

        String tripSQL = "SELECT r FROM ReqTrip r WHERE reqTripId = :reqTripId";
        List<ReqTrip> trips = jpaApi.em().createQuery(tripSQL, ReqTrip.class).setParameter("reqTripId", reqTripId).getResultList();

        String destinationSQL = "SELECT NEW models.UserDestination (d.destinationId, d.destinationName) " +
                "FROM Destination d " +
                "JOIN ReqTripDestination r on d.destinationId = r.destinationId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserDestination> userDestinations = jpaApi.em().createQuery(destinationSQL, UserDestination.class).setParameter("reqTripId", reqTripId).getResultList();

        String accommodationSQL = "SELECT NEW models.UserAccommodation (a.accommodationTypeId, a.accommodationTypeName) " +
                "FROM AccommodationType a " +
                "JOIN ReqTripAccommodation r ON a.accommodationTypeId = r.accommodationTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserAccommodation> userAccommodations = jpaApi.em().createQuery(accommodationSQL, UserAccommodation.class).setParameter("reqTripId", reqTripId).getResultList();

        String activitySQL = "SELECT NEW models.UserActivity (a.activityTypeName) " +
                "FROM ActivityType a " +
                "JOIN ReqTripActivity r ON a.activityTypeId = r.activityTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserActivity> userActivities = jpaApi.em().createQuery(activitySQL, UserActivity.class).setParameter("reqTripId", reqTripId).getResultList();

        String diningSQL = "SELECT NEW models.UserDining (d.diningTypeId, d.diningTypeName) " +
                "FROM DiningType d " +
                "JOIN ReqTripDining r ON d.diningTypeId = r.diningTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserDining> userDinings = jpaApi.em().createQuery(diningSQL, UserDining.class).setParameter("reqTripId", reqTripId).getResultList();

        String transportationSQL = "SELECT NEW models.UserTransportation (t.transportationTypeId, t.transportationTypeName) " +
                "FROM TransportationType t " +
                "JOIN ReqTripTransportation r ON t.transportationTypeId = r.transportationTypeId " +
                "JOIN ReqTrip rt ON r.reqTripId = rt.reqTripId " +
                "WHERE rt.reqTripId = :reqTripId ";
        List<UserTransportation> userTransportations = jpaApi.em().createQuery(transportationSQL, UserTransportation.class).setParameter("reqTripId", reqTripId).getResultList();

        DynamicForm form = formFactory.form().bindFromRequest();
        String searchQuery = form.get("searchquery");

        PlacesSearchResult[] places;
        if(searchQuery != null)
        {
            places = Place.getPlace(searchQuery);
        }
        else
        {
            places = Place.getPlace("Little Rock, AR");
        }
        session().get("destination");



        /*
        for(int i = 0; i < places.length; i++)
        {
            PlaceDetails details = Place.getPlaceDetails()places[i].placeId);

        }
        */

//        Accommodation newAccommodation = new Accommodation();
//        TripAccommodation newTripAccommodation = new TripAccommodation();
//        AccommodationType accommodationType = new AccommodationType();
//        String accommodation1 = form.get("accommodation1");
//        String accommodationInfo1 = form.get("accommodationinfo1");
//        String accommodation2 = form.get("accommodation2");
//        String accommodationInfo2 = form.get("accommodationinfo2");
//        String accommodation3 = form.get("accommodation3");
//        String accommodationInfo3 = form.get("accommodationinfo3");
//
//        newAccommodation.setAccommodationName(accommodation1);
//        newAccommodation.setAccommodationInformation(accommodationInfo1);
//        String newAccommodationSQL = "SELECT a FROM AccommodationType a WHERE AccommodationTypeName LIKE :accommodationType";
//
//        System.out.println("Hello World");
//        accommodationType.setAccommodationTypeName(accommodationInfo1);
//        jpaApi.em().persist(accommodationType);
//
//        newAccommodation.setAccommodationTypeId(accommodationType.getAccommodationTypeId());
//        jpaApi.em().persist(newAccommodation);
//
//        newTripAccommodation.setAccommodationId(newAccommodation.getAccommodationId());
//        newTripAccommodation.setReqTripId(reqTripId);
//        jpaApi.em().persist(newTripAccommodation);
//
//        accommodationType = new AccommodationType();
//        newAccommodation = new Accommodation();
//        newTripAccommodation = new TripAccommodation();
//
//        newAccommodation.setAccommodationName(accommodation2);
//        newAccommodation.setAccommodationInformation(accommodationInfo2);
//        String newAccommodationSQL = "SELECT a FROM AccommodationType a WHERE AccommodationTypeName LIKE :accommodationType";
//        try
//        {
//            accommodationType = jpaApi.em().createQuery(newAccommodationSQL, AccommodationType.class).setParameter("accommodationType", accommodationInfo2).getSingleResult();
//        }
//        catch(Exception e) {
//            accommodationType.setAccommodationTypeName(accommodationInfo2);
//            jpaApi.em().persist(accommodationType);
//        }
//        newAccommodation.setAccommodationTypeId(accommodationType.getAccommodationTypeId());
//        jpaApi.em().persist(newAccommodation);
//        newTripAccommodation.setAccommodationId(newAccommodation.getAccommodationId());
//        newTripAccommodation.setReqTripId(reqTripId);
//        jpaApi.em().persist(newTripAccommodation);
//
//        accommodationType = new AccommodationType();
//        newAccommodation = new Accommodation();
//        newTripAccommodation = new TripAccommodation();
//
//        newAccommodation.setAccommodationName(accommodation3);
//        newAccommodation.setAccommodationInformation(accommodationInfo3);
//        newAccommodationSQL = "SELECT a FROM AccommodationType a WHERE AccommodationTypeName LIKE :accommodationType";
//        try
//        {
//            accommodationType = jpaApi.em().createQuery(newAccommodationSQL, AccommodationType.class).setParameter("accommodationType", accommodationInfo3).getSingleResult();
//        }
//        catch(Exception e) {
//            accommodationType.setAccommodationTypeName(accommodationInfo2);
//            jpaApi.em().persist(accommodationType);
//        }
//        newAccommodation.setAccommodationTypeId(accommodationType.getAccommodationTypeId());
//        jpaApi.em().persist(newAccommodation);
//        newTripAccommodation.setAccommodationId(newAccommodation.getAccommodationId());
//        newTripAccommodation.setReqTripId(reqTripId);
//        jpaApi.em().persist(newTripAccommodation);
//
//
//
//
//
//


//
//        Activity newActivity = new Activity();
//        String activity1 = form.get("activity1");
//        String activityInfo1 = form.get("activityinfo1");
//        String activity2 = form.get("activity2");
//        String activityInfo2 = form.get("activityinfo2");
//        String activity3 = form.get("activity3");
//        String activityInfo3 = form.get("activityinfo3");
//        String activity4 = form.get("activity4");
//        String activityInfo4 = form.get("activityinfo4");
//        String activity5 = form.get("activity5");
//        String activityInfo5 = form.get("activityinfo5");
//        String activity6 = form.get("activity6");
//        String activityInfo6 = form.get("activityinfo6");
//        String activity7 = form.get("activity7");
//        String activityInfo7 = form.get("activityinfo7");
//        String activity8 = form.get("activity8");
//        String activityInfo8 = form.get("activityinfo8");
//        String activity9 = form.get("activity9");
//        String activityInfo9 = form.get("activityinfo9");
//        String activity10 = form.get("activity10");
//        String activityInfo10 = form.get("activityinfo10");
//
//        newActivity.setActivityName(activity1);
//        newActivity.setActivityInformation(activityInfo1);
//        newActivity.setActivityName(activity2);
//        newActivity.setActivityInformation(activityInfo2);
//        newActivity.setActivityName(activity3);
//        newActivity.setActivityInformation(activityInfo3);
//        newActivity.setActivityName(activity4);
//        newActivity.setActivityInformation(activityInfo4);
//        newActivity.setActivityName(activity5);
//        newActivity.setActivityInformation(activityInfo5);
//        newActivity.setActivityName(activity6);
//        newActivity.setActivityInformation(activityInfo6);
//        newActivity.setActivityName(activity7);
//        newActivity.setActivityInformation(activityInfo7);
//        newActivity.setActivityName(activity8);
//        newActivity.setActivityInformation(activityInfo8);
//        newActivity.setActivityName(activity9);
//        newActivity.setActivityInformation(activityInfo9);
//        newActivity.setActivityName(activity10);
//        newActivity.setActivityInformation(activityInfo10);
//        jpaApi.em().persist(newActivity);
//
//        Dining newDining = new Dining();
//        String dining1 = form.get("dining1");
//        String diningInfo1 = form.get("dininginfo1");
//        String dining2 = form.get("dining2");
//        String diningInfo2 = form.get("dininginfo2");
//        String dining3 = form.get("dining3");
//        String diningInfo3 = form.get("dininginfo3");
//        String dining4 = form.get("dining4");
//        String diningInfo4 = form.get("dininginfo4");
//        String dining5 = form.get("dining5");
//        String diningInfo5 = form.get("dininginfo5");
//        String dining6 = form.get("dining6");
//        String diningInfo6 = form.get("dininginfo6");
//        String dining7 = form.get("dining7");
//        String diningInfo7 = form.get("dininginfo7");
//        String dining8 = form.get("dining8");
//        String diningInfo8 = form.get("dininginfo8");
//        String dining9 = form.get("dining9");
//        String diningInfo9 = form.get("dininginfo9");
//        String dining10 = form.get("dining10");
//        String diningInfo10 = form.get("dininginfo10");
//
//        newDining.setDiningName(dining1);
//        newDining.setDiningInformation(diningInfo1);
//        newDining.setDiningName(dining2);
//        newDining.setDiningInformation(diningInfo2);
//        newDining.setDiningName(dining3);
//        newDining.setDiningInformation(diningInfo3);
//        newDining.setDiningName(dining4);
//        newDining.setDiningInformation(diningInfo4);
//        newDining.setDiningName(dining5);
//        newDining.setDiningInformation(diningInfo5);
//        newDining.setDiningName(dining6);
//        newDining.setDiningInformation(diningInfo6);
//        newDining.setDiningName(dining7);
//        newDining.setDiningInformation(diningInfo7);
//        newDining.setDiningName(dining8);
//        newDining.setDiningInformation(diningInfo8);
//        newDining.setDiningName(dining9);
//        newDining.setDiningInformation(diningInfo9);
//        newDining.setDiningName(dining10);
//        newDining.setDiningInformation(diningInfo10);
//        jpaApi.em().persist(newDining);
//
//        Transportation newTransportation = new Transportation();
//        String transportation = form.get("transportation");
//        String transportationInfo = form.get("transportationinfo");
//
//        newTransportation.setTransportationName(transportation);
//        newTransportation.setTransportationInformation(transportationInfo);
//        jpaApi.em().persist(newTransportation);
//
//        ReqTrip newReqTrip = new ReqTrip();
//        String tripDescription = form.get("description");
//
//        newReqTrip.setConsultantNotes(tripDescription);
//        jpaApi.em().persist(newReqTrip);



        return ok(views.html.trip.render(userReqTrips, trips, userDestinations, userAccommodations, userActivities, userDinings, userTransportations, places));
    }


    @Transactional
    public Result getUsers()
    {
        String sql = "SELECT u FROM User u";
        List<User> users = jpaApi.em().createQuery(sql, User.class).getResultList();
        return ok(views.html.users.render(users));
    }

}
