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

        PlacesSearchResult[] places = Place.getPlace(searchQuery);
        session().get("destination");

        /*
        for(int i = 0; i < places.length; i++)
        {
            PlaceDetails details = Place.getPlaceDetails()places[i].placeId);

        }
        */

        Accommodation newAccommodation = new Accommodation();
        String accommodation1 = form.get("accommodation1");
        String accommodation2 = form.get("accommodation2");
        String accommodation3 = form.get("accommodation3");

        Activity newActivity = new Activity();
        String activity1 = form.get("activity1");
        String activity2 = form.get("activity2");
        String activity3 = form.get("activity3");
        String activity4 = form.get("activity4");
        String activity5 = form.get("activity5");
        String activity6 = form.get("activity6");
        String activity7 = form.get("activity7");
        String activity8 = form.get("activity8");
        String activity9 = form.get("activity9");
        String activity10 = form.get("activity10");

        Dining newDining = new Dining();
        String dining1 = form.get("dining1");
        String dining2 = form.get("dining2");
        String dining3 = form.get("dining3");
        String dining4 = form.get("dining4");
        String dining5 = form.get("dining5");
        String dining6 = form.get("dining6");
        String dining7 = form.get("dining7");
        String dining8 = form.get("dining8");
        String dining9 = form.get("dining9");
        String dining10 = form.get("dining10");

        Transportation newTransportation = new Transportation();

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
