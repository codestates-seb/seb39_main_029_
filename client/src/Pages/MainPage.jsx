import styled from "styled-components";
import MainNav from "../Components/MainNav";
import MainDiv from "../Components/MainDiv";
import TextAnimation2 from "../Components/TextAnimation2";

function MainPage() {
  return (
    <Container>
      <MainNav />
      <Body>
        <MainDiv />
        <TextAnimation2 />
      </Body>
      <WhiteBottom></WhiteBottom>
    </Container>
  );
}
const Container = styled.div`
  width: 100vmax;
`;

const Body = styled.div`
  border-radius: 0.5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: var(--theme-gray);
  margin: 100px 50px 0px 50px;
  height: 970px;
  background: linear-gradient(
    180deg,
    hsl(210, 8%, 15%) 0%,
    var(--theme-gray) 130%
  );
`;

const WhiteBottom = styled.div`
  position: relative;
  top: -105px;
  background-color: white;
  margin: 0px 30px;
  height: 300px;
  border-radius: 100% 100% 0% 0%;
`;

export default MainPage;
