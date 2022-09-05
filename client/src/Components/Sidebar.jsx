import styled from "styled-components";
import { IoEarthSharp } from "react-icons/io5";
import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import "../index";

function Sidebar() {
  const location = useLocation();
  const [Homeselect, setHomeselect] = useState(true);
  const [Questionselect, setQuestionselect] = useState(false);
  const [Tagselect, setTagselect] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (location.pathname === "/questions") {
      setHomeselect(false);
      setQuestionselect(true);
      setTagselect(false);
    } else if (location.pathname === "/tag") {
      setHomeselect(false);
      setQuestionselect(false);
      setTagselect(true);
    } else {
      setHomeselect(true);
      setQuestionselect(false);
      setTagselect(false);
    }
  }, []);

  const HomeHandleClick = () => {
    setHomeselect(true);
    setQuestionselect(false);
    setTagselect(false);
    navigate("/home");
  };

  const QuestionHandleClick = () => {
    setHomeselect(false);
    setQuestionselect(true);
    setTagselect(false);
    navigate("/questions");
  };

  const TagHandleClick = () => {
    setHomeselect(false);
    setQuestionselect(false);
    setTagselect(true);
    navigate("/tag");
  };

  return (
    <Side>
      {Homeselect ? (
        <SelectedHome onClick={HomeHandleClick}>Home</SelectedHome>
      ) : (
        <Home onClick={HomeHandleClick}>Home</Home>
      )}
      <Public>PUBLIC</Public>
      <List>
        <div
          className={`${Questionselect ? "selectedquestions" : "question"}`}
          onClick={QuestionHandleClick}
        >
          <IoEarthSharp className="earth" /> Questions
        </div>
        <div
          className={`${Tagselect ? "selectedtags" : "tags"}`}
          onClick={TagHandleClick}
        >
          Tags
        </div>
      </List>
    </Side>
  );
}

const Side = styled.div`
  width: 200px;
  height: 100;
  border-right: 1px solid hsl(210, 8%, 85%);
  padding: 20px 0 0 0px;
  font-family: var(--sans-serif);
`;
const Home = styled.div`
  display: flex;
  align-items: center;
  padding-left: 10px;
  width: 200px;
  height: 34px;
  font-size: 15px;
  color: #6a737c;
  cursor: pointer;
`;

const SelectedHome = styled.div`
  display: flex;
  align-items: center;
  width: 185px;
  height: 34px;
  padding-left: 10px;
  background-color: #f1f2f3;
  font-weight: bold;
  font-size: 15px;
  color: black;
  cursor: pointer;
  border-right: 5px solid orange;
`;

const Public = styled.div`
  margin-top: 17px;
  font-size: 12px;
  padding-left: 10px;
  color: #6a737c;
`;

const List = styled.div`
  color: #525960;
  font-size: 15px;
  .question {
    margin-top: 2px;
    display: flex;
    align-items: center;
    width: 200px;
    height: 34px;
    cursor: pointer;
  }
  .selectedquestions {
    margin-top: 2px;
    display: flex;
    align-items: center;
    width: 195px;
    height: 34px;
    color: black;
    background-color: #f1f2f3;
    border-right: 5px solid orange;
    font-weight: bold;
    cursor: pointer;
  }
  .earth {
    margin: 10px;
    width: 18px;
    height: 18px;
  }
  .tags {
    padding-left: 40px;
    display: flex;
    align-items: center;
    width: 200px;
    height: 34px;
    cursor: pointer;
  }
  .selectedtags {
    padding-left: 40px;
    display: flex;
    align-items: center;
    width: 155px;
    height: 34px;
    cursor: pointer;
    color: black;
    background-color: #f1f2f3;
    border-right: 5px solid orange;
    font-weight: bold;
  }
`;

export default Sidebar;
