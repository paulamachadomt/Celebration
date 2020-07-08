import React from 'react';

import TopMenu from '../../components/TopMenu';
import LoginForm from '../../components/LoginForm';
import HomeCard from '../../components/HomeCard';

import './styles.scss';
import plan from "../../assets/plan.svg";
import guest from "../../assets/guest.svg";
import dinner from "../../assets/dinner.svg";

const Home = () => {
  return (
    <div className="main">
      
      <TopMenu />
      
      <div className="main-content">
        <div className="container">
          <div className="main-center">
            <div className="main-center-left">
              <h1>Organize os convidados e as comidas da sua festa.</h1>
              <h2>Confirme sua presença e registre sua contribuição.</h2>
            </div>
            
            <div className="main-center-divider"></div>
            
            <div className="main-center-right">
              <LoginForm />
            </div>
          </div>
          
          <div className="main-bottom">
            <HomeCard
              cardImage={plan}
              cardTitle="Planeje seu Evento"
              cardText="Lorem ipsum dolor sit amet, consectetur adipisicing elit."
            />
            <HomeCard
              cardImage={guest}
              cardTitle="Controle de convidados"
              cardText="Lorem ipsum dolor sit amet, consectetur adipisicing elit."
            />
            <HomeCard
              cardImage={dinner}
              cardTitle="Organize a comida"
              cardText="Lorem ipsum dolor sit amet, consectetur adipisicing elit."
            />
          </div>
        </div>
      </div>
    </div>
  )
}

export default Home;