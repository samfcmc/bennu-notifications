'use strict';

(function(module) {

  module.exports = function(context, name) {
    var libs = context.libs;
    var ReactRouter = libs.ReactRouter;
    var RouteHandler = ReactRouter.RouteHandler;
    var React = libs.React;
    var user = context.user;
    var Partials = context.components.Partials;
    var Navbar = Partials.Navbar;

    context.createComponent(name, 'Main', {
      render: function() {
        return (
          <div className="container">
            <Navbar user={user}/>
            <RouteHandler/>
          </div>
        );
      }
    });

  };

}(module));
