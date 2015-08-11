'use strict';

(function(module) {

  module.exports = function(context, name) {
    var Notification = context.components.Test.Notification;

    context.createComponent(name, 'List', {
      render: function() {
        var self = this;
        return (
          <div className="row">
            <div className="row">
              <h2>Notifications</h2>
            </div>
            <div className="row">
              <ul className="list-groupt">
                {this.props.list.map(function(item) {
                  return (
                    <Notification notification={item} key={item.id}
                      onRead={self.props.onRead}/>
                  );
                })}
              </ul>
            </div>
          </div>
        );
      }
    });

  };

}(module));
