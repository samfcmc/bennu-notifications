'use strict';

(function(module) {

  module.exports = function(React) {
    var New = React.createClass({
      render: function() {
        return (
          <div className="row">
            <ul className="list-group">
              <li className="list-group-item">
                <span className="badge">{this.props.new}</span>
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
