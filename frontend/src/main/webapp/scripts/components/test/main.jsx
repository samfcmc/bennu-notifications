'use strict';

(function(module) {

  module.exports = function(context, name) {
    var components = context.components.Test;
    var Generate = components.Generate;
    var Notifications = components.Notifications;

    context.createComponent(name, 'Main', {
      render: function() {
        return (
          <div className="row">
            <div className="row">
              <h2>Test</h2>
            </div>
            <div className="row">
              <div className="col-lg-6">
                <Generate></Generate>
              </div>
              <div className="col-lg-6">
                <Notifications></Notifications>
              </div>
            </div>
          </div>
        );
      }
    });

  };

}(module));
