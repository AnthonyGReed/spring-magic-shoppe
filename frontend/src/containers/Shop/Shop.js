import React, {useState} from 'react'
import Items from "../../components/Items/Items"
import Potions from '../../components/Potions/Potions'
import Scrolls from '../../components/Scrolls/Scrolls'
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Toast from 'react-bootstrap/Toast'
import {useLocation} from "react-router-dom";
import {Row, Col} from "react-bootstrap";

const Shop = (props) => {
    const[showToast, setShowToast] = useState(false);

    const toggleShowToast = () => setShowToast(!showToast)

    let location = useLocation()
    return(
        <div>
            <Row className={"info-row"}>
                <Col>
                    <p className="shopId">Shop ID: {props.data.id.toUpperCase()}</p>
                </Col>
                <Col>
                    <Toast show={showToast} onClose={toggleShowToast} position={'top-center'} delay={3000} autohide>
                        <Toast.Header>
                            <strong className="me-auto">System</strong>
                        </Toast.Header>
                        <Toast.Body>URL successfully Copied!</Toast.Body>
                    </Toast>
                </Col>
                <Col>
                    <Button onClick={() => {
                        navigator.clipboard.writeText("https://apps.seriousbusiness.network/magic/" + location.search).then(
                            () => {
                                setShowToast(true);
                            }
                        );
                        }}>Share
                    </Button>
                </Col>
            </Row>
            <Table responsive>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Gold Cost</th>
                        <th>Charges</th>
                        <th>Stones/Charge</th>
                        <th>Page</th>
                    </tr>
                </thead>
                <tbody>
                    <Items data={props.data.selectedWonders}/>
                </tbody>
            </Table>
            <Table responsive>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Gold Cost</th>
                        <th>Doses</th>
                        <th>Page</th>
                    </tr>
                </thead>
                <tbody>
                    <Potions data={props.data.selectedPotions}/>
                </tbody>
            </Table>
            <Table responsive>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Gold Cost</th>
                        <th>Page</th>
                    </tr>
                </thead>
                <tbody>
                    <Scrolls data={props.data.selectedScrolls}/>
                </tbody>
            </Table>
        </div>
    )
}
export default Shop