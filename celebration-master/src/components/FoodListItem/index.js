import React from 'react';
import './styles.scss';

const FoodListItem = ({ 
  food, 
  party, 
  loggedUser,
  confirmation,
  onClickConfirmation 
}) => {
  const userIsHost = loggedUser.id === party.host.id;
  // console.log("guestId: ", guestId);
  
    function getName(id) {
      const guestName = party.guests.find(guest => guest);
      console.log(guestName);
      return guestName;
    }
  const guestId = food.guest;
  const guestName = party.guests.find(getName);
  console.log("guestName: ", guestName);
  console.log("guestId: ", guestId);

  return (
    <div className="container">
      <div className="list-item">
        <span className="list-item-name">{food.item}</span>
        {/* <span className="list-item-name">{guestName}</span> */}

        {/* {userIsGuest 
        ? (
          <label className="checkbox" onClick={onClickConfirmation}>
            <input type="checkbox" name="presence" id="presence"/>
            <span className="checkmark"></span>
          </label>
          ) : guest.confirmation === true ? (
          <span>Confirmado</span>
          ) : (
            <span>Não confirmado</span>
          )} */}


      </div>
    </div>
  )
}

export default FoodListItem;