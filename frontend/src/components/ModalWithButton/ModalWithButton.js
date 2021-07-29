import React from "react";
import {Button, Modal, Col} from "react-bootstrap";

const ModalWithButton = (props) => {
    return (
        <>
            <Col>
                <Button variant="outline-primary" onClick={() => {props.handleShow()}}> {props.buttonText} </Button>
            </Col>
            <Modal show={props.show} onHide={() => {props.handleClose()}}>
                <Modal.Header>
                    <Modal.Title>
                        {props.modalTitle}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {props.children}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={() => {props.functionSubmit(); props.handleClose();}}>Submit</Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default ModalWithButton