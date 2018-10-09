package controllers;

import com.google.common.io.Files;
import models.ExistingTrip;
import models.Restriction;
import models.User;
import models.UserRestriction;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.HashHelper;

import javax.inject.Inject;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class UserController extends Controller
{

    private FormFactory formFactory;
    private JPAApi jpaApi;

    @Inject
    public UserController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getSignUp()
    {
        return ok(views.html.usersignup.render());
    }

    @Transactional
    public Result postSignUp()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        User newUser = new User();

        String email = form.get("email");
        String password = form.get("password");
        String firstName = form.get("firstname");
        String lastName = form.get("lastname");
        String zipCode = form.get("zipcode");
        LocalDate birthDate = LocalDate.parse(form.get("birthdate"));
        String restriction = form.get("restriction");

        newUser.setEmailAddress(email);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setZipCode(zipCode);
        newUser.setBirthDate(birthDate);
        newUser.setRestriction(restriction);
        jpaApi.em().persist(newUser);

        int userId = newUser.getUserId();

        /*
        String emailSQL = "SELECT u FROM User u WHERE u.EmailAddress = :email";
        User user = jpaApi.em().createQuery(emailSQL, User.class).setParameter("email", email).getSingleResult();

        if(emailSQL.equals(user.getEmailAddress()))
        {
            result = "dang";
            // return html page that says try again
            // return ok()
        }

        else
        {

        }
        */

        // if password shitty AF make better. test69 = puts some caps in that bitch, or a symbol that MF cause that's weak AF

              // Gets the user id from the new user input

        session().put("userId", "" + userId);   // Stores user id in the session scope
        session().put("firstName", firstName);    // Stores the username in the session scope
        session().put("lastName", lastName);

        return redirect("/userprofile");
    }

    @Transactional
    public Result getUserInformation()
    {
        String userIdText = session().get("userId");
        int userId = Integer.parseInt(userIdText);

        String sql = "SELECT u FROM User u WHERE userId = :userId";
        User user = jpaApi.em().createQuery(sql, User.class).setParameter("userId", userId).getSingleResult();

        return ok(views.html.userinformation.render(userId, user));
    }

    @Transactional
    public Result postUserInformation()
    {
        DynamicForm form = formFactory.form().bindFromRequest(); // Play framework: FormFactory for HTML interaction
        String userIdText = session().get("userId");
        int userId = Integer.parseInt(userIdText);

        String sql = "SELECT u FROM User u WHERE userId = :userId";
        User user = jpaApi.em().createQuery(sql, User.class).setParameter("userId", userId).getSingleResult();

        String emailAddress = form.get("emailaddress");
        String oldPassword = form.get("oldpassword");
        String newPassword = form.get("newpassword");
        String firstName = form.get("firstname");
        String lastName = form.get("lastname");
        String zipCode = form.get("zipcode");
        LocalDate birthDate = LocalDate.parse(form.get("birthdate"));
        byte[] savePhoto;

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("photo");
        File file = filePart.getFile();

        try
        {
            savePhoto = Files.toByteArray(file);
            if(savePhoto != null && savePhoto.length > 0)
            {
                user.setPhoto(savePhoto);
            }

        }

        catch (Exception e)
        {
            savePhoto = null;
        }

        user.setEmailAddress(emailAddress);

        if (oldPassword.equals(user.getPassword()))
        {
            user.setPassword(newPassword);
        }
        System.out.println(user.getFirstName() + "|" + user.getLastName());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setZipCode(zipCode);
        user.setBirthDate(birthDate);

        jpaApi.em().persist(user);

        session().put("firstName", firstName);

        return redirect("/userprofile");
    }

    @Transactional
    public Result getUserPhoto(int userId)
    {
        String sql = "SELECT u FROM User u WHERE userId = :userId";
        User user = jpaApi.em().createQuery(sql, User.class).setParameter("userId", userId).getSingleResult();

        return ok(user.getPhoto()).as("image/jpg");
    }

    @Transactional
    public Result getUserProfile()
    {
        String userIdText = session().get("userId");
        int userId = Integer.parseInt(userIdText);
        String firstName = session().get("firstName");

        String sql = "SELECT u FROM User u WHERE userId = :userId";
        User user = jpaApi.em().createQuery(sql, User.class).setParameter("userId", userId).getSingleResult();

        String reqTripSQL = "SELECT NEW models.ExistingTrip(rx.userId, r.reqTripId, d.destinationName, rx.startDate, rx.endDate, rx.tripPurpose) " +
                "FROM ReqTripDestination r " +
                "JOIN Destination d ON r.destinationId = d.destinationId " +
                "JOIN ReqTrip rx ON r.reqTripId = rx.reqTripId " +
                "JOIN User u ON u.userId = rx.userId " +
                "WHERE rx.userId = :userId";

        List<ExistingTrip> existingTrips = jpaApi.em().createQuery(reqTripSQL, ExistingTrip.class).setParameter("userId", userId).getResultList();

        Logger.debug("userId" + userId);
        return ok(views.html.userprofile.render(userId, firstName, user, existingTrips));
    }

    @Transactional
    public Result getUserLogin()
    {
        session().clear();
        return ok(views.html.userlogin.render());
    }

    @Transactional
    public Result postUserLogin()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        User returningUser;
        User tempUser = new User();
        String email = form.get("email");
        String password = form.get("psw");

        try
        {
            String sql = "SELECT u FROM User u WHERE EmailAddress = :email";
            returningUser = jpaApi.em().createQuery(sql, User.class).setParameter("email", email).getSingleResult();
        }
        catch(Exception e)
        {
            return ok("No Email Found Bitch");
        }

        System.out.println(returningUser.getEmailAddress() + "   " + returningUser.getPassword());

        System.out.println(email + "   " + tempUser.getPassword());


        if(returningUser.getEmailAddress().equals(email) && HashHelper.checkPassword(password, returningUser.getPassword()))
        {
            System.out.println("success");
            int userId = returningUser.getUserId();
            String firstName = returningUser.getFirstName();
            System.out.println(userId + " is the id for " + firstName);
            session().put("userId","" + userId);
            session().put("firstName", firstName);
            return redirect("/userprofile");
        }

        System.out.println("failed");
        return ok("404");
    }

    @Transactional
    public Result getThis()
    {
        return ok(views.html.journeytemplate.render());
    }

    @Transactional
    public Result getViewRecommendations()
    {
        return ok(views.html.viewrecommendations.render());
    }

}
