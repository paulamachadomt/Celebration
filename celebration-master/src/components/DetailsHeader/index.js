import React from 'react';
import './styles.scss';

const DetailsHeader = ({ 
  party,
  isAdmin,
  confirmation,
  onClickConfirmation 
}) => {

  
  // console.log("confirmation no DetailsHeader: ", confirmation)


  return (
    <div className="details-header">
      <div className="details-header-container">
        <div className="details-header-left">
          <h2 className="details-header-left-title">
            {party.title}
          </h2>
          <div className="details-header-left-info">
            <span>Anfitrião: <strong>{party.host.name}</strong></span>
            <span>Local: <strong>{party.location}</strong></span>
          </div>
        </div>
        <div className="details-header-right">

          {!isAdmin && (confirmation 
            ? (
              <div className="details-header-button button" onClick={onClickConfirmation}>
                Confirmar Presença
              </div>
            ) : (
              <div className="details-header-button button active" onClick={onClickConfirmation}>
                Presença Confimada
              </div>
            ))}

            {isAdmin && (
              <div className="details-header-button button" onClick={onClickConfirmation}>
                Salvar Edição
              </div>
            )}    

        </div>
      </div>
    </div>
  )
}

export default DetailsHeader;