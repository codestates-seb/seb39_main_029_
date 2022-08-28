import styled from "styled-components";
import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import PostBox from "../Components/PostBox";
import Button from "../Assets/Button";

function QuestionPage() {
  return (
    <Container>
      <Nav />
      <Sidebar />
      <Body>
        <Title>
          <Titletop>
            <span className="top">All Questions</span>
            <Button
              text={"Ask Question"}
              bgcolor="var(--theme-blue)"
              padding={10}
              color="var(--font-color-white)"
              ftsize={13}
            />
          </Titletop>
          <Titlebottom></Titlebottom>
        </Title>
        {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map(() => (
          <PostBox />
        ))}
      </Body>
    </Container>
  );
}

const Container = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: auto;
  height: auto;
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 50px;
  margin-left: 240px;
  width: 1232px;
`;

const Title = styled.div`
  width: 1032px;
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

export default QuestionPage;
