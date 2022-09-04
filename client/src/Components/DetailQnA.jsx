import styled from "styled-components";
import "../index";
import Vote from "../Assets/Imgs/vote";
import ColorButton from "../Assets/ColorBtn";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

function DetailQnA() {
  const navigate = useNavigate();
  const toAsk = () => {
    navigate("/ask");
  };

  const token = localStorage.getItem("accessToken");
  const [QnA, setQnA] = useState({});
  const [Comments, setComments] = useState([]);

  useEffect(() => {
    axios
      .get("http://seb039pre029.ga:8080/v1/posts/1", {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setQnA(res.data);
        setComments(res.data.commentsList);
      });
  }, []);

  console.log(QnA);
  console.log(Comments);

  const time = String(QnA.createAt).substr(0, 10);

  const AllComments = () => {
    for (let i = 0; i < Comments.length; i++) {
      return Comments[i];
    }
  };

  console.log(AllComments);

  return (
    <Wrapper>
      <TitleWrapper>
        <div className="title">{QnA.subject}</div>
        <div className="status">
          <div>
            <span className="ask">Asked</span>
            <span className="createdAt">{time}</span>
          </div>
          <ColorButton mode="BLUE" text="Ask Question" onClick={toAsk} />
        </div>
      </TitleWrapper>
      <QWrapper>
        <div className="qstatus">
          <Vote className="rockt" />
          <div className="votes">{QnA.votes}</div>
        </div>
        <div className="qlist">
          <div className="Q">{QnA.content}</div>
          <div className="change">
            <button>Edit</button>
            <button>Delete</button>
          </div>
        </div>
      </QWrapper>
      <AWrapper>
        <div className="answerList">{QnA.commentsCount} Answers</div>
        <div className="abox">
          <div className="astatus">
            <Vote className="rockt" />
            <div className="votes">13</div>
          </div>
          <div className="A">If you want to get index and item:</div>
        </div>
      </AWrapper>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 30px;
  font-family: var(--sans-serif);
  font-size: var(--text-font);
  .votes {
    font-size: var(--ad-font);
    margin: 10px 0 0 0;
  }
  .rockt {
    cursor: pointer;
  }
`;
const TitleWrapper = styled.div`
  border-bottom: 1px solid hsl(210, 8%, 85%);
  padding: 20px;
  margin: 0 0 20px 0;
  .title {
    font-size: var(--header-font);
    margin: 0 0 10px 0;
  }
  .status {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  > span {
    font-size: var(--normal-font);
  }
  .ask {
    color: var(--font-color-gray);
  }
  .createdAt {
    margin: 0 0 0 5px;
  }
`;
const QWrapper = styled.div`
  display: flex;
  padding: 20px;
  margin: 0 0 20px 20px;
  .qstatus {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .qlist {
    display: flex;
    flex-direction: column;
    margin: 0 0 0 30px;
  }
  .Q {
    margin: 0 0 20px 0;
  }
  .change {
    > button {
      margin: 0 10px 0 0;
      padding: 3px 8px;
      border: 1px solid hsl(210, 8%, 85%);
      border-radius: 5px;
    }
  }
`;
const AWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
  margin: 0 0 20px 20px;
  .answerList {
    font-size: var(--ad-font);
    margin: 0 0 20px 0;
  }
  .astatus {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .abox {
    display: flex;
  }
  .A {
    margin: 0 0 0 30px;
  }
`;

export default DetailQnA;
