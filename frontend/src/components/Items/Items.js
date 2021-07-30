import React, {useState} from 'react'
import Axios from 'axios'
import Badge from 'react-bootstrap/Badge'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'
import Page from '../Page/Page'

function Items(props) {
    const [itemData, setItemData] = useState("");
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const getWonderData = (id) => {
      setItemData("Loading")
      Axios.get("api/wonders/" + id).then(result => {
        setItemData(result.data)
        handleShow()
      })
    }

    const items = props.data.map((item, index) => {
      return(<tr key={index} onClick={() => getWonderData(item.wonder.id)}>
        <td><span className={`dot ${item.wonder.rarity.toUpperCase().split(" ").join("")}`}></span>
            {item.wonder.name} {item.onSale && <Badge pill>Sale!</Badge>}</td>
        <td>{item.wonder.type}</td>
        <td>{item.gold}</td>
        <td>{item.charges}</td>
        <td>{item.stones}</td>
        <td><Page data={item.wonder.page} /></td>
        </tr>)
      }
    )

    return(
      <>
        {items}
        <Modal show={show} onHide={handleClose}>
          <Modal.Header>
            <Modal.Title>{itemData.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {itemData.attunement && <p><em>Requires Attunement</em></p>}
            {itemData.limits && <p><strong>Limits: </strong>{itemData.limits}</p>}
            <p><strong>Description: </strong>{itemData.description}</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={handleClose}>Ok</Button>
          </Modal.Footer>
        </Modal>
      </>
    )
}

export default Items;