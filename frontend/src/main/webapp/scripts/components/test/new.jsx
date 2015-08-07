'use strict';

(function(module) {

  module.exports = function(React) {
    var New = React.createClass({
      getInitialState: function() {
        return {
          new: 0
        };
      },
      render: function() {
        return (
          <div className="row">
            <ul className="list-group">
              <li className="list-group-item">
                <span className="badge">{this.state.new}</span>
                New notifications
              </li>
            </ul>
          </div>
        );
      }
    });

    return New;
  }

}(module));
