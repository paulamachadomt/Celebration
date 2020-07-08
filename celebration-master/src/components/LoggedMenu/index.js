import React from 'react';
import { Link } from 'react-router-dom';
import loggedUser from '../../utils/mocks/loggedUser-mock';
import logoInverse from "../../assets/logo-inverse.svg";
import './styles.scss';

const InverseMenu = () => {
  return (
    <div className="inverse-menu">
      <div className="inverse-menu-container">
        <div className="inverse-menu-logo">
          <Link to="/dashboard">
            <img className="inverse-menu-logo-image" src={logoInverse} alt="Celebration Logo" />
          </Link>
        </div>
        <nav className="inverse-menu-nav">
          <Link className="inverse-menu-link" to="/">Criar Evento</Link>
          <Link className="inverse-menu-link" to="/history">Histórico</Link>
          <Link className="inverse-menu-link" to="/settings">Configurações</Link>
        </nav>
        <div className="inverse-menu-user">
          <Link className="inverse-menu-link" to="/">
            Olá, 
            <strong>{loggedUser.name}</strong>
          </Link>
        </div>
      </div>
    </div>
  )
}

export default InverseMenu;