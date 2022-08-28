import HomePage from "./Pages/HomePage";
import { Routes, Route } from "react-router-dom";
import QuestionPage from "./Pages/QuestionPage";
import MainPage from "./Pages/MainPage";
import SignUp from "./Components/SignUp";
import LogIn from "./Components/LogIn";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />}></Route>
      <Route path="/home" element={<HomePage />}></Route>
      <Route path="/questions" element={<QuestionPage />}></Route>
      <Route path="/signup" element={<SignUp />}></Route>
      <Route path="/login" element={<LogIn />}></Route>
    </Routes>
  );
}

export default App;
