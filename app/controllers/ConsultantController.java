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

        PlacesSearchResult[] places = Place.getPlace("kensington, london, restaurants");
        PlaceDetails[] placesDetails = new PlaceDetails[places.length];

        for(int i = 0; i < places.length; i++)
        {
            placesDetails[i] = Place.getPlaceDetails(places[i].placeId);
        }

        System.out.println("there are " + placesDetails[0].name);
        return ok(views.html.trip.render(userReqTrips, trips, userDestinations, userAccommodations, userActivities, userDinings, userTransportations, placesDetails));
    }

    @Transactional
    public Result postTrip(int reqTripId) throws InterruptedException, ApiException, IOException
    {
        Boolean searchSubmit = false;
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
        PlaceDetails[] placesDetails;
        if(searchQuery != null)
        {
            places = Place.getPlace(searchQuery);
            placesDetails = new PlaceDetails[places.length];
            searchSubmit = true;
            for(int i = 0; i < places.length; i++)
            {
                placesDetails[i] = Place.getPlaceDetails(places[i].placeId);
            }
        }
        else
        {
            places = Place.getPlace("Little Rock, AR");
            placesDetails = new PlaceDetails[places.length];
            for(int i = 0; i < places.length; i++)
            {
                placesDetails[i] = Place.getPlaceDetails(places[i].placeId);
            }
        }
        session().get("destination");



        /*
        for(int i = 0; i < places.length; i++)
        {
            PlaceDetails details = Place.getPlaceDetails()places[i].placeId);

        }
        */
        if(searchSubmit != true)
        {
            Accommodation newAccommodation = new Accommodation();
            TripAccommodation newTripAccommodation = new TripAccommodation();

            String accommodation1 = form.get("accommodation1");
            String accommodationInfo1 = form.get("accommodationinfo1");
            String accommodation2 = form.get("accommodation2");
            String accommodationInfo2 = form.get("accommodationinfo2");
            String accommodation3 = form.get("accommodation3");
            String accommodationInfo3 = form.get("accommodationinfo3");

            if(accommodation1.length() > 0 || accommodationInfo1.length() > 0)
            {
                newAccommodation.setAccommodationName(accommodation1);
                newAccommodation.setAccommodationInformation(accommodationInfo1);
                jpaApi.em().persist(newAccommodation);

                newTripAccommodation.setAccommodationId(newAccommodation.getAccommodationId());
                newTripAccommodation.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripAccommodation);
            }

            if(accommodation2.length() > 0 || accommodationInfo2.length() > 0)
            {
                newAccommodation = new Accommodation();
                newTripAccommodation = new TripAccommodation();
                newAccommodation.setAccommodationName(accommodation2);
                newAccommodation.setAccommodationInformation(accommodationInfo2);
                jpaApi.em().persist(newAccommodation);

                newTripAccommodation.setAccommodationId(newAccommodation.getAccommodationId());
                newTripAccommodation.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripAccommodation);
            }

            if(accommodation3.length() > 0 || accommodationInfo3.length() > 0)
            {
                newAccommodation = new Accommodation();
                newTripAccommodation = new TripAccommodation();
                newAccommodation.setAccommodationName(accommodation3);
                newAccommodation.setAccommodationInformation(accommodationInfo3);
                jpaApi.em().persist(newAccommodation);

                newTripAccommodation.setAccommodationId(newAccommodation.getAccommodationId());
                newTripAccommodation.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripAccommodation);
            }

            Activity newActivity = new Activity();
            TripActivity newTripActivity = new TripActivity();

            String activity1 = form.get("activity1");
            String activityInfo1 = form.get("activityinfo1");
            String activity2 = form.get("activity2");
            String activityInfo2 = form.get("activityinfo2");
            String activity3 = form.get("activity3");
            String activityInfo3 = form.get("activityinfo3");
            String activity4 = form.get("activity4");
            String activityInfo4 = form.get("activityinfo4");
            String activity5 = form.get("activity5");
            String activityInfo5 = form.get("activityinfo5");
            String activity6 = form.get("activity6");
            String activityInfo6 = form.get("activityinfo6");
            String activity7 = form.get("activity7");
            String activityInfo7 = form.get("activityinfo7");
            String activity8 = form.get("activity8");
            String activityInfo8 = form.get("activityinfo8");
            String activity9 = form.get("activity9");
            String activityInfo9 = form.get("activityinfo9");
            String activity10 = form.get("activity10");
            String activityInfo10 = form.get("activityinfo10");

            if(activity1.length() > 0 || activityInfo1.length() > 0)
            {
                newActivity.setActivityName(activity1);
                newActivity.setActivityInformation(activityInfo1);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity2.length() > 0 || activityInfo2.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity2);
                newActivity.setActivityInformation(activityInfo2);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity3.length() > 0 || activityInfo3.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity3);
                newActivity.setActivityInformation(activityInfo3);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity4.length() > 0 || activityInfo4.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity4);
                newActivity.setActivityInformation(activityInfo4);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity5.length() > 0 || activityInfo5.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity5);
                newActivity.setActivityInformation(activityInfo5);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity6.length() > 0 || activityInfo6.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity6);
                newActivity.setActivityInformation(activityInfo6);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity7.length() > 0 || activityInfo7.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity7);
                newActivity.setActivityInformation(activityInfo7);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity8.length() > 0 || activityInfo8.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity8);
                newActivity.setActivityInformation(activityInfo8);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity9.length() > 0 || activityInfo9.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity9);
                newActivity.setActivityInformation(activityInfo9);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            if(activity10.length() > 0 || activityInfo10.length() > 0)
            {
                newActivity = new Activity();
                newTripActivity = new TripActivity();
                newActivity.setActivityName(activity10);
                newActivity.setActivityInformation(activityInfo10);
                jpaApi.em().persist(newActivity);

                newTripActivity.setActivityId(newActivity.getActivityId());
                newTripActivity.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripActivity);
            }

            Dining newDining = new Dining();
            TripDining newTripDining = new TripDining();

            String dining1 = form.get("dining1");
            String diningInfo1 = form.get("dininginfo1");
            String dining2 = form.get("dining2");
            String diningInfo2 = form.get("dininginfo2");
            String dining3 = form.get("dining3");
            String diningInfo3 = form.get("dininginfo3");
            String dining4 = form.get("dining4");
            String diningInfo4 = form.get("dininginfo4");
            String dining5 = form.get("dining5");
            String diningInfo5 = form.get("dininginfo5");
            String dining6 = form.get("dining6");
            String diningInfo6 = form.get("dininginfo6");
            String dining7 = form.get("dining7");
            String diningInfo7 = form.get("dininginfo7");
            String dining8 = form.get("dining8");
            String diningInfo8 = form.get("dininginfo8");
            String dining9 = form.get("dining9");
            String diningInfo9 = form.get("dininginfo9");
            String dining10 = form.get("dining10");
            String diningInfo10 = form.get("dininginfo10");

            if(dining1.length() > 0 || diningInfo1.length() > 0)
            {
                newDining.setDiningName(dining1);
                newDining.setDiningInformation(diningInfo1);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining2.length() > 0 || diningInfo2.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining2);
                newDining.setDiningInformation(diningInfo2);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining3.length() > 0 || diningInfo3.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining3);
                newDining.setDiningInformation(diningInfo3);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining4.length() > 0 || diningInfo4.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining4);
                newDining.setDiningInformation(diningInfo4);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining5.length() > 0 || diningInfo5.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining5);
                newDining.setDiningInformation(diningInfo5);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining6.length() > 0 || diningInfo6.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining6);
                newDining.setDiningInformation(diningInfo6);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining7.length() > 0 || diningInfo7.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining7);
                newDining.setDiningInformation(diningInfo7);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining8.length() > 0 || diningInfo8.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining8);
                newDining.setDiningInformation(diningInfo8);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining9.length() > 0 || diningInfo9.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining9);
                newDining.setDiningInformation(diningInfo9);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }

            if(dining10.length() > 0 || diningInfo10.length() > 0)
            {
                newDining = new Dining();
                newTripDining = new TripDining();
                newDining.setDiningName(dining10);
                newDining.setDiningInformation(diningInfo10);
                jpaApi.em().persist(newDining);

                newTripDining.setDiningId(newDining.getDiningId());
                newTripDining.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripDining);
            }


            Transportation newTransportation = new Transportation();
            TripTransportation newTripTransportation = new TripTransportation();

            String transportation1 = form.get("transportation1");
            String transportationInfo1 = form.get("transportationinfo1");
            String transportation2 = form.get("transportation2");
            String transportationInfo2 = form.get("transportationinfo2");
            String transportation3 = form.get("transportation3");
            String transportationInfo3 = form.get("transportationinfo3");

            if(transportation1.length() > 0 || transportationInfo1.length() > 0)
            {
                newTransportation.setTransportationName(transportation1);
                newTransportation.setTransportationInformation(transportationInfo1);
                jpaApi.em().persist(newTransportation);

                newTripTransportation.setTransportationId(newTransportation.getTransportationId());
                newTripTransportation.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripTransportation);
            }

            if(transportation2.length() > 0 || transportationInfo2.length() > 0)
            {
                newTransportation = new Transportation();
                newTripTransportation = new TripTransportation();
                newTransportation.setTransportationName(transportation2);
                newTransportation.setTransportationInformation(transportationInfo2);
                jpaApi.em().persist(newTransportation);

                newTripTransportation.setTransportationId(newTransportation.getTransportationId());
                newTripTransportation.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripTransportation);
            }

            if(transportation3.length() > 0 || transportationInfo3.length() > 0)
            {
                newTransportation = new Transportation();
                newTripTransportation = new TripTransportation();
                newTransportation.setTransportationName(transportation3);
                newTransportation.setTransportationInformation(transportationInfo3);
                jpaApi.em().persist(newTransportation);

                newTripTransportation.setTransportationId(newTransportation.getTransportationId());
                newTripTransportation.setReqTripId(reqTripId);
                jpaApi.em().persist(newTripTransportation);
            }


            String descriptionSQL = "SELECT r FROM ReqTrip r WHERE reqtripId = :reqTripId";
            ReqTrip newReqTrip = jpaApi.em().createQuery(descriptionSQL, ReqTrip.class).setParameter("reqTripId", reqTripId).getSingleResult();
            newReqTrip.getBudget();
            String tripDescription = form.get("description");

            if(tripDescription.length() > 0)
            {
                newReqTrip.setConsultantNotes(tripDescription);
                jpaApi.em().persist(newReqTrip);
            }
        }

        return ok(views.html.trip.render(userReqTrips, trips, userDestinations, userAccommodations, userActivities, userDinings, userTransportations, placesDetails));
    }


    @Transactional
    public Result getUsers()
    {
        String sql = "SELECT u FROM User u";
        List<User> users = jpaApi.em().createQuery(sql, User.class).getResultList();
        return ok(views.html.users.render(users));
    }

}
