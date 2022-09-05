import styled from "styled-components";
import "../index";
import Vote from "../Assets/Imgs/vote";
import ColorButton from "../Assets/ColorBtn";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import TextEditor from "./TextEditor";
import ReactMarkdown from "react-markdown";

function DetailQnA() {
  const location = useLocation();
  const url = location.pathname.substring(5);

  const navigate = useNavigate();
  const toAsk = () => {
    navigate("/ask");
  };

  //? 데이터 받아오기
  const token = localStorage.getItem("accessToken");
  const memberId = localStorage.getItem("memberId");

  const [QnA, setQnA] = useState({});
  const [Comments, setComments] = useState([]);
  const [tBtn, settBtn] = useState([]);

  useEffect(() => {
    axios
      .get(`http://seb039pre029.ga:8080/v1/posts/${url}`, {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setQnA(res.data);
        setComments(res.data.commentsList);
        settBtn(res.data.postTag);
        setVo(res.data.votes);
      });
  }, [Comments]);

  const time = String(QnA.createAt).substring(0, 10);

  //? 게시글 수정 및 삭제
  const onHandleDelete = () => {
    axios
      .delete(`http://seb039pre029.ga:8080/v1/posts/${url}`, {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setQnA(res.data);
        setComments(res.data.commentsList);
      });
    navigate("/home");
  };

  //? 투표기능
  const [vo, setVo] = useState();

  const handleVote = () => {
    axios
      .patch(
        `http://seb039pre029.ga:8080/v1/posts/upVotes/${url}?memberId=${memberId}`,
        {},
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then((res) => setVo(res.data.votes));
  };

  const handleCommentVote = (id) => {
    axios
      .patch(
        `http://seb039pre029.ga:8080/v1/comments/upvotes/${id}?memberid=${memberId}`,
        {},
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then((res) => console.log(res.data.votes));
  };

  //? 답글 기능
  const [reply, setReply] = useState("");

  const editForm = {
    userid: memberId,
    postid: url,
    contents: reply,
  };

  const Comment = () => {
    axios
      .post("http://seb039pre029.ga:8080/v1/comments", editForm, {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        console.log(res);
      });
  };

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
          <Vote className="rck" onClick={handleVote} />
          <div className="votes">{vo}</div>
        </div>
        <div className="qlist">
          <div className="change">
            <Link
              className="btn"
              to={{ pathname: `/edit/${url}`, state: { data: QnA } }}
            >
              <ColorButton mode={"BLUE"} text={"Edit"} />
            </Link>
            <ColorButton
              mode={"BLUE"}
              text={"Delete"}
              onClick={onHandleDelete}
            />
          </div>
          <div className="Q">
            <ReactMarkdown>{QnA.content}</ReactMarkdown>
          </div>
          <div className="twrap">
            {tBtn.map((el) => {
              return (
                <span className="tags" key={el.tagId}>
                  {el.name}
                </span>
              );
            })}
          </div>
        </div>
      </QWrapper>
      <AWrapper>
        <div className="answerList">{QnA.commentsCount} Answers</div>
        {Object.values(Comments).map((el) => {
          return (
            <AMap key={el.commentsid}>
              <div className="abox">
                <div className="astatus">
                  <button
                    className="btn"
                    onClick={() => handleCommentVote(el.commentsid)}
                  >
                    <Vote className="rck" />
                  </button>
                  <div className="votes">{el.votes}</div>
                </div>
                <ReactMarkdown className="A">{el.content}</ReactMarkdown>
              </div>
            </AMap>
          );
        })}
      </AWrapper>
      <NewA>
        <TextEditor setContent={setReply} />
        <ColorButton mode="BLUE" text="Submit" onClick={Comment} />
      </NewA>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0 30px 30px 30px;
  font-family: var(--sans-serif);
  font-size: var(--text-font);
  .votes {
    font-size: var(--ad-font);
    margin: 10px 0 0 0;
  }
  .rck {
    cursor: pointer;
    opacity: 0.5;
    fill: black;
    :active {
      opacity: 1;
      fill: var(--theme-orange);
    }
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
    margin: 50px 0 20px 0;
  }
  .change {
    width: 150px;
    display: flex;
    justify-content: flex-start;
  }
  .btn {
    margin: 0 10px 0 0;
    text-decoration-line: none;
  }
  .twrap {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
  }
  .tags {
    margin: 20px 10px 0 0;
    padding: 5px 8px;
    background-color: var(--theme-button-blue);
    color: var(--font-color-teal);
    font-size: var(--small-font);
    border-radius: 0.1rem;
  }
`;
const AWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
  margin: 0 0 20px 20px;
  .answerList {
    font-size: var(--ad-font);
    margin: 0 0 50px 0;
  }
`;
const AMap = styled.div`
  .astatus {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .abox {
    display: flex;
    margin: 0 0 50px 0;
    padding: 0 0 30px 0;
    border-bottom: 1px solid hsl(210, 8%, 85%);
  }
  .A {
    align-self: center;
    margin: 0 0 0 30px;
  }
  .btn {
    border: none;
    background-color: #ffffff;
  }
`;
const NewA = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  margin: 0 0 0 30px;
  width: 100;
`;

export default DetailQnA;
