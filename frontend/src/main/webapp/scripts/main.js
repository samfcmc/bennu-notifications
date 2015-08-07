'use strict';

(function (require, React, Router, ReactBootstrap, moment, jQuery) {
  var NotificationsClient = require('./notifications');
  var components = require('./components')(React, Router, NotificationsClient,
    ReactBootstrap, moment);
  var Main = components.Main;
  var Welcome = components.Welcome;
  var Admin = components.Admin;
  var Test = components.Test;
  var Route = Router.Route;


  var Routes = (
    <Route handler={Main} path="/">
      <Route handler={Admin} path="admin"/>
      <Route handler={Test} path="test"/>
      <Route handler={Welcome} path="*"/>
    </Route>
  )

  jQuery.ajax('/api/bennu-core/profile', {
    method: 'GET',
    success: function(response) {
      if(response.username) {
        Router.run(Routes, Router.HashLocation, function(Handler) {
          React.render(
            <Handler/>,
            document.getElementById('content')
          );
        });
      }
      else {
        window.location = '/login';
      }
    },
    error: function(err) {
      console.error(err);
    }
  });



}(require, window.React, window.ReactRouter, window.ReactBootstrap,
  window.moment, jQuery));
