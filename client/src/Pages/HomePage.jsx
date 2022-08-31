import styled from "styled-components";
import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import PostBox from "../Components/PostBox";
import Button from "../Assets/Button";
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../index";

function HomePage() {
  const [totalPosts, setTotalPosts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(
        "http://ec2-15-165-204-159.ap-northeast-2.compute.amazonaws.com:8080/v1/posts?page=1&size=10&arrange=createdAt&tagCheckId=-1"
      )
      .then((res) => {
        setTotalPosts(res.data.data);
      });
  }, []);

  return (
    <>
      <Nav />
      <Wrapper>
        <Sidebar />
        <Body>
          <Titletop>
            <div className="top">Top Questions</div>
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
  font-family: var(--sans-serif);
  display: flex;
`;

const Body = styled.div`
  height: 100vmax;
  width: 100%;
`;

const Titletop = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  height: 100px;
  border-bottom: 1px solid hsl(210, 8%, 85%);
  .top {
    font-weight: bold;
    font-size: var(--header-font);
  }
`;
const Titlebottom = styled.div``;

export default HomePage;
