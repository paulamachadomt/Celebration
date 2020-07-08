import React from 'react';
import { Link } from 'react-router-dom';
import './styles.scss';

const LoginForm = () => {
  return (
    <div className="login-form">
      <span className="login-form-title">Faça seu login</span>
      <input type="email" name="email" id="email" placeholder="E-mail" />
      <input type="text" placeholder="CPF" />
      <Link className="button" to="/dashboard">
        <span>Login</span>
      </Link>
      <span className="login-form-register">Não tem uma conta? <Link to="#">Cadastre-se aqui</Link>.</span>
    </div>
  )
}

export default LoginForm;