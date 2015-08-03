'use strict';

(function(module) {

  module.exports = function(React, Notification) {
    var List = React.createClass({
      getInitialState: function() {
        return {
          data: []
        };
      },
      render: function() {
        var rows = [];
        var data = this.state.data;
        for(var i = 0; i < data.length; i++) {
          var row = data[i];
          rows.push(<Notification data={row}/>);
        }
        return (
          <div className="row">
            <div className="row">
              <h2>{this.props.type}</h2>
            </div>
            <div className="row">
              <ul className="list-groupt">
                {rows}
              </ul>
            </div>
          </div>
        );
      }
    });

    return List;
  }

}(module));
