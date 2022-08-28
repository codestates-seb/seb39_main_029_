import styled from "styled-components";
import { IoEarthSharp } from "react-icons/io5";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Sidebar() {
  const [Homeselect, setHomeselect] = useState(true);
  const [Questionselect, setQuestionselect] = useState(false);
  const [Tagselect, setTagselect] = useState(false);
  const navigate = useNavigate();

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
  };

  return (
    <Side>
      {Homeselect ? (
        <SelectedHome>Home</SelectedHome>
      ) : (
        <Home
          onClick={() => {
            HomeHandleClick();
          }}
        >
          Home
        </Home>
      )}
      <Public>PUBLIC</Public>
      <List>
        {Questionselect ? (
          <div className="selectedquestions">
            <IoEarthSharp className="earth" /> Questions
          </div>
        ) : (
          <div
            className="question"
            onClick={() => {
              QuestionHandleClick();
            }}
          >
            <IoEarthSharp className="earth" /> Questions
          </div>
        )}
        {Tagselect ? (
          <div className="selectedtags">Tags</div>
        ) : (
          <div
            className="tags"
            onClick={() => {
              TagHandleClick();
            }}
          >
            Tags
          </div>
        )}
      </List>
    </Side>
  );
}

const Side = styled.div`
  position: absolute;
  top: 50px;
  left: 0px;
  width: 240px;
  height: 100%;
  border-right: 1px solid var(--font-color-gray);
`;
const Home = styled.div`
  display: flex;
  align-items: center;
  margin-top: 20px;
  padding-left: 10px;
  width: 230px;
  height: 34px;
  font-size: 13px;
  color: #6a737c;
  cursor: pointer;
`;

const SelectedHome = styled.div`
  display: flex;
  align-items: center;
  margin-top: 20px;
  padding-left: 10px;
  width: 225px;
  height: 34px;
  background-color: #f1f2f3;
  font-weight: bold;
  font-size: 13px;
  color: black;
  cursor: pointer;
  border-right: 5px solid orange;
`;

const Public = styled.div`
  margin-left: 10px;
  margin-top: 17px;
  font-size: 11px;
  color: #6a737c;
`;

const List = styled.div`
  color: #525960;
  font-size: 13px;
  .question {
    margin-top: 2px;
    display: flex;
    align-items: center;
    width: 240px;
    height: 34px;
    cursor: pointer;
  }
  .selectedquestions {
    margin-top: 2px;
    display: flex;
    align-items: center;
    width: 235px;
    height: 34px;
    color: black;
    background-color: #f1f2f3;
    border-right: 5px solid orange;
    font-weight: bold;
    cursor: pointer;
  }
  .earth {
    margin: 0px 8px;
    padding: 0px;
    width: 18px;
    height: 18px;
  }
  .tags {
    padding-left: 35px;
    display: flex;
    align-items: center;
    width: 205px;
    height: 34px;
    cursor: pointer;
  }
  .selectedtags {
    padding-left: 35px;
    display: flex;
    align-items: center;
    width: 200px;
    height: 34px;
    cursor: pointer;
    color: black;
    background-color: #f1f2f3;
    border-right: 5px solid orange;
    font-weight: bold;
  }
`;

export default Sidebar;
