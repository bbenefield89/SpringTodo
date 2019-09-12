// import React from 'react';
// import logo from './logo.svg';
// import './App.css';

// const App: React.FC = () => {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.tsx</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

// export default App;


import React from "react";
import NavBar from "./components/NavBar";

// New - import the React Router components, and the Profile page component
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Profile from "./components/Profile";
import PrivateRoute from "./components/PrivateRoute";

function App() {
  return (
    <div className="App">
      {/* New - use BrowserRouter to provide access to /profile */}
      <BrowserRouter>
        <header>
          <NavBar />
        </header>
        <Switch>
          <Route path="/" exact />
          <PrivateRoute path="/profile" component={Profile} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;