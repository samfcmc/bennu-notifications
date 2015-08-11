'use strict';

(function(module) {
  module.exports = function(context, name){
    context.createComponent(name, 'Welcome', {
      render: function() {
        return (
          <div className="jumbotron">
            <h1>Notifications Service</h1>
            <p>Here you can manage your notifications service instance</p>
          </div>
        );

      }
    });

  }
}(module));
