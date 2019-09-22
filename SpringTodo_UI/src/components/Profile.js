// src/components/Profile.js

import React from "react";
import { useAuth0 } from "../react-auth0-wrapper";

const Profile = () => {
    const { loading, user, getIdTokenClaims, getTokenSilently } = useAuth0();

    if (loading || !user) {
        return (
            <div>Loading...</div>
        );
    }

    return (
        <>
            {test()}
        
            <img src={user.picture} alt="Profile" />

            <h2>{user.name}</h2>
            <p>{user.email}</p>
            <code>{JSON.stringify(user, null, 2)}</code>
        </>
    );

    function test() {
        getIdTokenClaims()
            .then(res => console.log(res))
            .catch(err => console.log(err))

        getTokenSilently()
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }
};

export default Profile;