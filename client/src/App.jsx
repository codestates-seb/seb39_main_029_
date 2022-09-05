import HomePage from "./Pages/HomePage";
import { Routes, Route } from "react-router-dom";
import QuestionPage from "./Pages/QuestionPage";
import MainPage from "./Pages/MainPage";
import SignUpPage from "./Pages/SignupPage";
import LogInPage from "./Pages/LoginPage";
import LogOutPage from "./Pages/LogoutPage";
import PostWritePage from "./Pages/PostWritePage";
import PostEditPage from "./Pages/PostEditPage";
import TagPage from "./Pages/TagPage";
import TagSearchPage from "./Pages/TagSearchPage";
import DetailPage from "./Pages/DetailPage";
import MyPage from "./Pages/MyPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />}></Route>
      <Route path="/signup" element={<SignUpPage />}></Route>
      <Route path="/login" element={<LogInPage />}></Route>
      <Route path="/logout" element={<LogOutPage />}></Route>
      <Route path="/home" element={<HomePage />}></Route>
      <Route path="/questions" element={<QuestionPage />}></Route>
      <Route path="/tag" element={<TagPage />}></Route>
      <Route path="/tagsearch:tid" element={<TagSearchPage />}></Route>
      <Route path="/ask" element={<PostWritePage />}></Route>
      <Route path="/edit/:pid" element={<PostEditPage />}></Route>
      <Route path="/qna/:pid" element={<DetailPage />}></Route>
      <Route path="/info" element={<MyPage />}></Route>
    </Routes>
  );
}

export default App;
