/*
 * Routes definition
 */

(function(module) {

  module.exports = function(Router, components) {
    var Route = Router.Route;

    var Main = components.Main;
    var Welcome = components.Welcome;
    var Admin = components.Admin;
    var Test = components.Test;

    var Routes = (
      <Route handler={Main} path="/">
        <Route handler={Admin} path="admin"/>
        <Route handler={Test} path="test"/>
        <Route handler={Welcome} path="*"/>
      </Route>
    );

    return Routes;
  }

}(module));
