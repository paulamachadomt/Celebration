import React, { useState } from 'react';
import './styles.scss';

const userSchema = {
  id: '',
  cpf: '',
  name: '',
  partyHost: [],
  partyGuest: []
}

const AddListItem = ({ 
  party,
  activeTab,
  guestList,
  setGuestList
}) => {
  const [isAdding, setIsAdding] = useState(false);
  const [user, setUser] = useState(userSchema);
  const [food, setFood] = useState([])

  const onClickAdding = () => {
    return isAdding === false && setIsAdding(!isAdding);
  }

  const changeGuestName = (e) => {
    let name = e.target.value;
    return setUser({ ...user, name: name });
  }

  const changeGuestCPF = (e) => {
    let cpf = e.target.value;
    return setUser({ ...user, cpf: cpf });
  }

  const handleFood = (e) => {
    let food = e.target.value;
    console.log("food: ", food)
    // return setFood([...food, food])
  }

  const createContribuition = (e) => {
    e.preventDefault();

  }

  const createGuest = (e) => {
    e.preventDefault();
    const newGuest = user;
    setGuestList([...guestList, newGuest]);
    setIsAdding(!isAdding)
  }

  return (
    <div className="container">
      <div className={`list-item ${!isAdding ? 'is-clickable' : ''}`} onClick={onClickAdding}>

          {!isAdding 
            ? (
              <div className="list-item-icon">
                <svg xmlns="http://www.w3.org/2000/svg" fill="#FFFFFF" width="24" height="24" viewBox="0 0 24 24">
                  <path d="M24 9h-9v-9h-6v9h-9v6h9v9h6v-9h9z"/>
                </svg>
              </div>
              
            ) 
            : activeTab === 'convidados' ? (
              <form action="create-guest" method="post">
                <input 
                  className="list-item-textinput" 
                  type="text" 
                  maxLength={70}
                  name="name"
                  value={user.name}
                  onChange={changeGuestName}                  
                  placeholder="Digite o nome do convidado" 
                />
                <input 
                  className="list-item-textinput" 
                  type="text" 
                  maxLength={70}
                  name="cpf"
                  value={user.cpf}
                  onChange={changeGuestCPF}                  
                  placeholder="Digite o CPF do convidado" 
                />
                <input 
                  className="list-item-buttoninput button" 
                  type="button" 
                  value="Salvar"
                  onClick={createGuest}
                />
              </form>
            ) : (
              <form action="create-contribuition" method="post">
                <input 
                  className="list-item-textinput" 
                  type="text" 
                  maxLength={70}
                  name="food"
                  value={user.name}
                  onChange={handleFood}                  
                  placeholder="Digite o item para contribuição" 
                />
                <input 
                  className="list-item-buttoninput button" 
                  type="button" 
                  value="Salvar"
                  onClick={createContribuition}
                />
              </form>
            )
          }
        
      </div>
    </div>
  )
}

export default AddListItem;