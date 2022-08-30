import HomePage from "./Pages/HomePage";
import { Routes, Route } from "react-router-dom";
import QuestionPage from "./Pages/QuestionPage";
import MainPage from "./Pages/MainPage";
import SignUpPage from "./Pages/SignupPage";
import LogInPage from "./Pages/LoginPage";
import PostWritePage from "./Pages/PostWritePage";
import PostEditPage from "./Pages/PostEditPage";
import TagPage from "./Pages/TagPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />}></Route>
      <Route path="/signup" element={<SignUpPage />}></Route>
      <Route path="/login" element={<LogInPage />}></Route>
      <Route path="/home" element={<HomePage />}></Route>
      <Route path="/questions" element={<QuestionPage />}></Route>
      <Route path="/ask" element={<PostWritePage />}></Route>
      <Route path="/edit" element={<PostEditPage />}></Route>
      <Route path="/tag" element={<TagPage />}></Route>
    </Routes>
  );
}

export default App;
