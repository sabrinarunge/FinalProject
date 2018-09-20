package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller
{

    public Result getSignUp()
    {
        return ok(views.html.usersignup.render());
    }

    public Result getUserInput()
    {
        return ok(views.html.userinput.render());
    }
}
