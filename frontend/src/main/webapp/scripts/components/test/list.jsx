'use strict';

(function(module) {

  module.exports = function(React, Notification) {
    var List = React.createClass({
      render: function() {
        return (
          <div className="row">
            <div className="row">
              <h2>Notifications</h2>
            </div>
            <div className="row">
              <ul className="list-groupt">
                {this.props.list.map(function(item) {
                  return (
                    <Notification notification={item} key={item.id}/>
                  );
                })}
              </ul>
            </div>
          </div>
        );
      }
    });

    return List;
  }

}(module));
