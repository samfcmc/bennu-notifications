'use strict';

(function(module) {
  module.exports = function(context, name) {

    context.createComponent(name, 'Config', {
      render: function() {
        return (
          <div className="col-lg-12">
            <h2>Configuration</h2>
          </div>
        );
      }
    });

  }

}(module))
