import styled from "styled-components";
import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import PostBox from "../Components/PostBox";
import Button from "../Assets/Button";
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function HomePage() {
  const [totalPosts, setTotalPosts] = useState([]);
  const navigate = useNavigate();
  const token = localStorage.getItem("accessToken");

  useEffect(() => {
    axios
      .get(
        `${process.env.REACT_APP_STACKOVERFLOW}/v1/posts?page=1&size=10&arrange=createdAt&tagCheckId=-1`,
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then((res) => {
        setTotalPosts(res.data.data);
      });
  }, []);

  return (
    <>
      <Nav setTotalPosts={setTotalPosts} totalPosts={totalPosts} />
      <Wrapper>
        <Sidebar />
        <Body>
          <Titletop>
            <span className="top">Top Questions</span>
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
          <Titlebottom></Titlebottom>

          {totalPosts.map((el, idx) => (
            <PostBox key={idx} post={el} />
          ))}
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
`;
const Titletop = styled.div`
  display: flex;
  height: 40px;
  justify-content: space-between;
  align-items: flex-start;
  margin-top: 20px;
  height: 100px;
  border-bottom: 1px solid hsl(210, 8%, 85%);
  .top {
    margin-left: 20px;
    font-weight: 500;
    font-size: var(--header-font);
  }
`;
const Titlebottom = styled.div``;

export default HomePage;
