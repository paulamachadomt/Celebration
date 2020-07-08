import React, { Fragment, useState, useEffect } from 'react';
import LoggedMenu from '../../components/LoggedMenu';
import DetailsList from '../../components/DetailsList';
import DetailsHeader from '../../components/DetailsHeader';
import loggedUser from '../../utils/mocks/loggedUser-mock';
import './styles.scss';

const Details = ({ party }) => {
  const [confirmation, setConfirmation] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);


  const onClickConfirmation = () => {
    setConfirmation(!confirmation);
  }

  useEffect(() => {
    const isUserAdmin = () => {
      if(loggedUser.id === party.host.id) {
        setIsAdmin(true);
      }
    }
    
    isUserAdmin();
  }, [loggedUser.id, party.host]);

  return (
    <Fragment>

      <LoggedMenu />

      <DetailsHeader 
        party={party}
        isAdmin={isAdmin}
        loggedUser={loggedUser}
        confirmation={confirmation}
        setConfirmation={setConfirmation}
        onClickConfirmation={onClickConfirmation}
      />

      <DetailsList 
        party={party}
        isAdmin={isAdmin}
        loggedUser={loggedUser}
        confirmation={confirmation}
        setConfirmation={setConfirmation}
        onClickConfirmation={onClickConfirmation}
      />
    
    </Fragment>
  )
}

export default Details;