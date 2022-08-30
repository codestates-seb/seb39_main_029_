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
    <Container>
      <Nav />
      <Wrapper>
        <Sidebar />
        <Body>
          <Title>
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
          </Title>
          {totalPosts.map((el, idx) => (
            <PostBox key={idx} post={el} />
          ))}
        </Body>
      </Wrapper>
    </Container>
  );
}

const Container = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: auto;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 50px;
`;

const Title = styled.div`
  width: 100%;
  height: 130px;
`;
const Titletop = styled.div`
  display: flex;
  margin-top: 20px;
  margin-left: 20px;
  height: 40px;
  .top {
    font-size: var(--header-font);
    margin-right: 730px;
  }
`;
const Titlebottom = styled.div``;

export default HomePage;
