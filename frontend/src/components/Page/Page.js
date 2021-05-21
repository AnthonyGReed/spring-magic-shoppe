import React from 'react'

function Page(props) {
    let page = props.data
        .replace("phb", "Player's Handbook")
        .replace("dmg", "Dungeon Master's Guide")
        .replace("ee", "Elemental Evil")
        .replace("xge", "Xanathar's Guide")
        .replace("tce", "Tasha's Cauldron")
        .replace("pat", "Patron Created")
        .replace("ctm", "Custom")
        .replace("sca", "Sword Coast Adventurer's Guide")
    
    return(
        <>{page}</>
    )
}

export default Page