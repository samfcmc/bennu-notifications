/*
 * Routes definition
 */

(function(module) {

  module.exports = function(context) {
    var libs = context.libs;
    var Router = libs.ReactRouter;
    var components = context.components;
    var Route = Router.Route;

    var Main = components.Main.Main;
    var Welcome = components.Main.Welcome;
    var Admin = components.Admin.Main;
    var Test = components.Test.Main;

    context.Routes = (
      <Route handler={Main} path="/">
        <Route handler={Admin} path="admin"/>
        <Route handler={Test} path="test"/>
        <Route handler={Welcome} path="*"/>
      </Route>
    );

  }

}(module));
