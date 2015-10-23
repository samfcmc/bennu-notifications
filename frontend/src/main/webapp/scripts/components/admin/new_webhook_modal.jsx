'use strict';

(function(module) {
  module.exports = function(context, name) {
    var ReactBootstrap = context.libs.ReactBootstrap;
    var Modal = ReactBootstrap.Modal;
    var Input = ReactBootstrap.Input;
    var Glyphicon = ReactBootstrap.Glyphicon;
    var Button = ReactBootstrap.Button;

    context.createComponent(name, 'NewWebhook', {
      render: function() {
        return (
          <Modal show={this.props.showModal} onHide={this.props.close}>
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
              <Button onClick={this.props.close}>
                <Glyphicon glyph="remove" />
                Cancel
              </Button>
              <Button onClick={this.props.close}>
                <Glyphicon glyph="ok" />
                Save
              </Button>
            </Modal.Footer>
          </Modal>
        );
      }
    });

  }

}(module))
