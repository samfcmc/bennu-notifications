'use strict';

(function(module) {

  module.exports = function(context, name) {
    var libs = context.libs;
    var ReactRouter = libs.ReactRouter;
    var Link = ReactRouter.Link;
    var State = ReactRouter.State;
    var React = libs.React;
    context.createComponent(name, 'Navbar', {
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
                <ul className="nav navbar-nav navbar-right">
                  <li>
                    <a href="/logout">({this.props.user.username}) Logout</a>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        );
      }
    });

  }

}(module));
