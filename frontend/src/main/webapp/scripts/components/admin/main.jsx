'use strict';

(function(module) {
  module.exports = function(context, name) {
    context.createComponent(name, 'Main', {
      render: function() {
        return (
          <div className="row">
            <h2>Admin</h2>
          </div>
        );
      }
    });

  }

}(module))
