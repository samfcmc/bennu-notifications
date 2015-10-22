'use strict';

(function(module) {
  module.exports = function(context, name) {
    var ReactBootstrap = context.libs.ReactBootstrap;
    var Button = ReactBootstrap.Button;
    var Glyphicon = ReactBootstrap.Glyphicon;
    var Modal = ReactBootstrap.Modal;
    var Input = ReactBootstrap.Input;

    context.createComponent(name, 'Webhooks', {
      getInitialState: function() {
        return {
          showModal: false
        };
      },
      showModal: function() {
        this.setState({showModal: true});
      },
      close: function() {
        this.setState({showModal: false});
      },
      render: function() {
        return (
          <div className="col-lg-12">
            <h2>Webhooks configuration</h2>
            <div className="row">
              <div className="col-lg-12">
                <Button bsStyle="success" onClick={this.showModal}>
                  <Glyphicon glyph="plus" />
                  Add webhook
                </Button>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                TODO: List of webhooks
              </div>
            </div>

            <Modal show={this.state.showModal} onHide={this.close}>
              <Modal.Header closeButton>
                <Modal.Title>Create a new webhook</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <form>
                  <Input type="text" label="Name" placeholder="Name..."/>
                  <Input type="select" label="Select an HTTP method">
                    <option value="post">POST</option>
                    <option value="put">PUT</option>
                    <option value="delete">DELETE</option>
                  </Input>
                  <Input type="text" label="URL" placeholder="URL..."/>
                  <Input type="textarea" label="Data" placeholder="Data {key: value, ...}" />
                </form>
              </Modal.Body>
              <Modal.Footer>
                <Button onClick={this.close}>
                  <Glyphicon glyph="remove" />
                  Cancel
                </Button>
                <Button onClick={this.close}>
                  <Glyphicon glyph="ok" />
                  Save
                </Button>
              </Modal.Footer>
            </Modal>

          </div>
        );
      }
    });

  }

}(module))
