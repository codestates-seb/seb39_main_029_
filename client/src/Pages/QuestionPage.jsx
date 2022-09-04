import styled from "styled-components";
import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import PostBox from "../Components/PostBox";
import Button from "../Assets/Button";
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function QuestionPage() {
  const [newestList, setNewestList] = useState([]);
  const [votedList, setVotedList] = useState({});
  const [page, setPage] = useState(1);
  const token = localStorage.getItem("accessToken");
  const [isNewest, setIsNewest] = useState(true);
  const [total, setTotal] = useState(0);
  const lastPage = Math.ceil(total / 10);
  const pageArr = Array.from({ length: lastPage }, (val, k) => k + 1);
  const navigate = useNavigate();

  console.log({ page });

  useEffect(() => {
    axios
      .get(
        `http://seb039pre029.ga:8080/v1/posts?page=${page}&size=10&arrange=createdAt&tagCheckId=-1`,
        { headers: { Authorization: token } }
      )
      .then((res) => {
        setNewestList(res.data.data);
        setTotal(res.data.pageInfo.totalElements);
      });
  }, [page]);

  useEffect(() => {
    axios
      .get(
        `http://seb039pre029.ga:8080/v1/posts?page=${page}&size=10&arrange=commentsCount&tagCheckId=-1`,
        { headers: { Authorization: token } }
      )
      .then((res) => {
        setVotedList(res.data.data);
        setTotal(res.data.pageInfo.totalElements);
      });
  }, [page]);

  return (
    <>
      <Nav />
      <Wrapper>
        <Sidebar />
        <Body>
          <Titletop>
            <span className="top">All Questions</span>
            <Button
              text={"Ask Question"}
              bgcolor="var(--theme-blue)"
              padding={10}
              color="var(--font-color-white)"
              ftsize={13}
              onClick={() => {
                navigate("/ask");
              }}
            />
          </Titletop>
          <Titlebottom>
            <span>{total} questions</span>
            <span>
              <button
                onClick={() => {
                  setIsNewest(true);
                }}
              >
                Newest
              </button>
              <button
                onClick={() => {
                  setIsNewest(false);
                }}
              >
                Answered
              </button>
            </span>
          </Titlebottom>
          {isNewest
            ? newestList.map((el, i) => <PostBox key={i} post={el} />)
            : votedList.map((el, i) => <PostBox key={i} post={el} />)}
          <ButtonWrapper>
            {page === 1 ? null : (
              <button
                onClick={() => {
                  setPage(page - 1);
                }}
              >
                Prev
              </button>
            )}

            {pageArr.map((el, i) => {
              return (
                <button
                  key={i}
                  className={`pagebutton ${el === page ? "focus" : ""}`}
                  onClick={() => {
                    setPage(el);
                  }}
                >
                  {el}
                </button>
              );
            })}
            {page === lastPage ? null : (
              <button
                onClick={() => {
                  setPage(page + 1);
                }}
              >
                Next
              </button>
            )}
          </ButtonWrapper>
        </Body>
      </Wrapper>
    </>
  );
}

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const Body = styled.div`
  height: 100vmax;
  width: 1200px;
  margin-bottom: 50px;
`;

const Titletop = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-top: 20px;
  height: 60px;

  .top {
    margin-left: 20px;
    font-weight: 500;
    font-size: var(--header-font);
  }
`;
const Titlebottom = styled.div`
  display: flex;
  justify-content: space-between;
  margin-left: 20px;
  border-bottom: 1px solid hsl(210, 8%, 85%);
  padding-bottom: 20px;
  span {
    &:first-child {
      font-size: 17px;
    }
  }
  button {
    border: 0.5px solid lightgray;
    background-color: white;
    padding: 10px;
    &:first-child {
      border-top-left-radius: 0.3rem;
      border-bottom-left-radius: 0.3rem;
    }
    &:nth-child(2) {
      border-top-right-radius: 0.3rem;
      border-bottom-right-radius: 0.3rem;
    }
    &:focus {
      background-color: #e3e6e8;
    }
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
  gap: 5px;
  margin: 30px;
  button {
    background-color: var(--theme-white);
    border: 0.5px solid lightgray;
    border-radius: 0.3rem;
    padding: 8px;
    cursor: pointer;
  }
  .pagebutton {
    &:focus {
      background-color: var(--theme-orange);
      color: var(--font-color-white);
    }
  }
  .focus {
    background-color: var(--theme-orange);
    color: var(--font-color-white);
  }
`;

export default QuestionPage;
