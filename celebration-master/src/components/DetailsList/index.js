import React, { useState, useEffect } from 'react';
import TabMenu from '../TabMenu';
import GuestListItem from '../GuestListItem';
import FoodListItem from '../FoodListItem';
import AddListItem from '../AddListItem';
import './styles.scss';

const DetailsList = ({ 
  party, 
  isAdmin,
  loggedUser,
  confirmation,
  onClickConfirmation 
}) => {
  const [CPF, setCPF] = useState("");
  const [name, setName] = useState("");
  const [guestList, setGuestList] = useState([]);
  const [foodList, setFoodList] = useState([]);
  const [activeTab, setActiveTab] = useState("convidados");

  useEffect(() => {
    setGuestList(party.guests);
  }, [party.guests]);

  useEffect(() => {
    setFoodList(party.contribuition);
  }, [])

  console.log("party no DetailsList: ", party)
  console.log("foodList no DetailsList: ", foodList)
  // console.log("confirmation no DetailsList: ", confirmation)

  return (
    <div className="container">
      {isAdmin && (
        <div className="container">
          <TabMenu 
            leftTab="convidados"
            rightTab="contribuicoes"
            leftTitle="Convidados"
            rightTitle="Comes e Bebes"
            activeTab={activeTab}
            setActiveTab={setActiveTab}
          />
        </div>
      )}

      <div style={{ display: activeTab !== 'convidados' ? 'none' : '', marginTop: 32 }}>
        <div className="list-header">
          <span>Nome do Convidado</span>
          <span>Contribuição</span>
          <span>Confirmação</span>
        </div>

        {guestList.map((guest, key) => {
          return (
            <GuestListItem 
              key={key} 
              guest={guest} 
              party={party}
              loggedUser={loggedUser} 
              confirmation={confirmation}
              onClickConfirmation={onClickConfirmation} 
            />
          )
        })}

        {isAdmin &&
          <AddListItem 
            CPF={CPF}
            name={name}
            party={party}
            setCPF={setCPF}
            setName={setName}
            guestList={guestList} 
            activeTab={activeTab}
            setGuestList={setGuestList} 
          />
        }
      </div>

      <div style={{ display: activeTab !== 'contribuicoes' ? 'none' : '' }}>
        <div className="list-header">
          <span>Comes e Bebes</span>
          <span>Convidado</span>
        </div>

        {foodList.map((food, key) => {
          return (
            <FoodListItem 
              key={key} 
              food={food} 
              party={party}
              loggedUser={loggedUser} 
              confirmation={confirmation}
              onClickConfirmation={onClickConfirmation} 
            />
          )
        })}

        {isAdmin &&
          <AddListItem 
            CPF={CPF}
            name={name}
            party={party}
            setCPF={setCPF}
            setName={setName}
            guestList={guestList} 
            activeTab={activeTab}
            setGuestList={setGuestList} 
          />
        }
      </div>
      
    </div>
  )
}

export default DetailsList;