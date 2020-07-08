import React from 'react';
import './styles.scss';

const GuestListItem = ({ 
  guest, 
  party, 
  loggedUser,
  confirmation,
  onClickConfirmation 
}) => {
  const userIsHost = loggedUser.id === party.host.id;
  const userIsGuest = loggedUser.id === guest.id;
  // console.log("party: ", party)
  // console.log("guest: ", guest)
  // console.log("userIsHost: ", userIsHost)
  // console.log("userIsGuest: ", userIsGuest)

  // console.log("confirmation no GuestListItem: ", confirmation)

  return (
    <div className="container">
      <div className="list-item">
        <span className="list-item-name">{guest.name}</span>

        {userIsGuest || userIsHost
        ? (
          <select 
            className="list-item-select"
            required
            id="contribuition" 
            name="contribuition" 
            // defaultValue="Selecione um item"
          >
            <option 
              defaultValue="Selecione um item" 
              disabled 
              // selected
            >Selecione um item</option>
            <option value="Laranja" selected>Laranja</option>
            <option value="Refrigerante">Refrigerante</option>
            <option value="Salgadinho">Salgadinho</option>
            <option value="Bolo">Bolo</option>
          </select>
        ) : (
          <span>{guest.contribuition}</span>
        )}

        {userIsGuest 
        ? (
          <label className="checkbox" onClick={onClickConfirmation}>
            <input type="checkbox" name="presence" id="presence"/>
            <span className="checkmark"></span>
          </label>
          ) : guest.confirmation === true ? (
          <span>Confirmado</span>
          ) : (
            <span>Não confirmado</span>
          )}


      </div>
    </div>
  )
}

export default GuestListItem;