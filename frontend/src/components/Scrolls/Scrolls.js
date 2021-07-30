import React, {useState} from 'react'
import Axios from 'axios'
import Badge from 'react-bootstrap/Badge'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import Page from '../Page/Page'

function Scrolls(props) {
    const [itemData, setItemData] = useState("");
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const getScrollData = (id) => {
        setItemData("Loading...")
        Axios.get("api/scrolls/" + id).then(result => {
          setItemData(result.data)
          handleShow()
        })
    }

    const scrolls = props.data.map((item, index) => {
      return(<tr key={index} onClick={() => getScrollData(item.scroll.id)} className={item.onSale ? "sale" : ""}>
        <td><span className={`dot ${item.leveledRarity}`}/>Scroll of {item.scroll.name}, {item.spellLevel}
            {item.spellLevel === 1 ? "st" : item.spelllevel === 2 ? "nd" : item.spellLevel === 3 ? "rd" : "th" } Level
            {item.scroll.onSale && <Badge pill>On Sale!</Badge>}</td>
        <td>{item.scroll.type}</td>
        <td>{item.gold}</td>
        <td><Page data={item.scroll.page} /></td>
      </tr>)
    })

    return (
      <>
        {scrolls}
        <Modal show={show} onHide={handleClose}>
          <Modal.Header>
            <Modal.Title>{itemData.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {itemData.school && <p><em>School of {itemData.school}</em></p>}
            {itemData.concentration && <p><em>Requires Concentration</em></p>}
            {itemData.ritual && <p><em>Ritual</em></p>}
            <p><strong>Range: </strong>{itemData.spellRange}</p>
            <p><strong>Classes: </strong>{itemData.classes}</p>
            <p><strong>Casting Time: </strong>{itemData.castingTime}</p>
            <p><strong>Components: </strong>{itemData.components}</p>
            <p><strong>Duration: </strong>{itemData.duration}</p>
            <p><strong>Description:</strong> Please see book.</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={handleClose}>Ok</Button>
          </Modal.Footer>
        </Modal>
      </>
    )

}

export default Scrolls;