'use strict';

(function(module) {

  module.exports = function(React, ReactRouter) {
    var Link = ReactRouter.Link;
    var State = ReactRouter.State;
    var Navbar = React.createClass({
      mixins: [State],
      render: function() {
        var currentRoute = this.getPathname();
        var adminClass = currentRoute == '/admin' ? 'active' : '';
        var testClass = currentRoute == '/test' ? 'active' : '';
        return (
          <nav className="navbar navbar-default">
            <div className="container-fluid">
              <div className="navbar-header">
                <button type="button" className="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#navbar-collapse"
                aria-expanded="false">
                  <span className="sr-only">Toggle navigation</span>
                  <span className="icon-bar"></span>
                  <span className="icon-bar"></span>
                  <span className="icon-bar"></span>
                </button>
                <a className="navbar-brand" href="#">Notifications Service</a>
              </div>

              <div className="collapse navbar-collapse" id="navbar-collapse">
                <ul className="nav navbar-nav">
                  <li className={adminClass}>
                    <Link to="/admin">Admin</Link>
                  </li>
                  <li className={testClass}>
                    <Link to="/test">Test</Link>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        );
      }
    });

    return Navbar;
  }

}(module));
