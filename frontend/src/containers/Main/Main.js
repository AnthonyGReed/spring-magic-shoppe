import React, { useState, useEffect, useRef } from 'react'
import {FormControl, Dropdown, DropdownButton} from 'react-bootstrap'
import ModalWithButton from "../../components/ModalWithButton/ModalWithButton";

const Main = (props) => {

    const [shopID, setShopID] = useState("");
    const [shopLevel, setShopLevel] = useState(1);

    const [showNewShopModal, setShowNewShopModal] = useState(false);
    const [showLoadShopModal, setShowLoadShopModal] = useState(false);

    const inputRef = useRef(null);

    useEffect(() => {
        if(showLoadShopModal) {
            inputRef.current.focus();
        }
    })

    const handleNewShopClose = () => setShowNewShopModal(false);
    const handleNewShopShow = () => setShowNewShopModal(true);

    const handleLoadShopClose = () => setShowLoadShopModal(false);
    const handleLoadShopShow = () => setShowLoadShopModal(true);

    const handleSelect =(e) => {
        setShopLevel(e);
    }

    const checkKey = (event) => {
        if(event.key === 'Enter') {
            props.load(shopID)
            handleLoadShopClose()
        }
    }

    const rollRandomNum = () => {
        return Math.floor(Math.random() * 10) + 1;
    }

    return (
        <>
            <ModalWithButton
                buttonText={"New Shop"}
                modalTitle={"Choose Shop Level"}
                functionSubmit={() => {props.new(shopLevel)}}
                handleShow={handleNewShopShow}
                handleClose={handleNewShopClose}
                show={showNewShopModal}>
                <DropdownButton onSelect={handleSelect} title={"Shop Level " + shopLevel}>
                    <Dropdown.Item eventKey={1}>Level 1</Dropdown.Item>
                    <Dropdown.Item eventKey={2}>Level 2</Dropdown.Item>
                    <Dropdown.Item eventKey={3}>Level 3</Dropdown.Item>
                    <Dropdown.Item eventKey={4}>Level 4</Dropdown.Item>
                    <Dropdown.Item eventKey={5}>Level 5</Dropdown.Item>
                    <Dropdown.Item eventKey={6}>Level 6</Dropdown.Item>
                    <Dropdown.Item eventKey={7}>Level 7</Dropdown.Item>
                    <Dropdown.Item eventKey={8}>Level 8</Dropdown.Item>
                    <Dropdown.Item eventKey={9}>Level 9</Dropdown.Item>
                    <Dropdown.Item eventKey={10}>Level 10</Dropdown.Item>
                    <Dropdown.Item eventKey={rollRandomNum()}>Random</Dropdown.Item>
                </DropdownButton>
            </ModalWithButton>
            <ModalWithButton
                buttonText={"Load Shop"}
                modalTitle={"Load Shop"}
                functionSubmit={() => {props.load(shopID)}}
                handleShow={handleLoadShopShow}
                handleClose={handleLoadShopClose}
                show={showLoadShopModal}
            >
                <FormControl
                    value={shopID}
                    onChange={e => setShopID(e.target.value)}
                    onKeyDown={checkKey}
                    placeholder={"Shop ID"}
                    ref={inputRef}
                />
            </ModalWithButton>
        </>
    )
}
                
                        
export default Main