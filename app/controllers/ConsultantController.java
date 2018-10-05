package controllers;

import com.google.maps.errors.ApiException;
import models.*;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class ConsultantController extends Controller
{
    private JPAApi jpaApi;

    @Inject
    public ConsultantController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
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

        Place newPlace = new Place();

        newPlace.getPlace();

        return ok(views.html.trip.render(userReqTrips, trips, userDestinations, userAccommodations, userActivities, userDinings, userTransportations));
    }

    @Transactional
    public Result getUsers()
    {
        String sql = "SELECT u FROM User u";
        List<User> users = jpaApi.em().createQuery(sql, User.class).getResultList();
        return ok(views.html.users.render(users));
    }

}
