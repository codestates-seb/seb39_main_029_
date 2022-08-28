import styled from "styled-components";
import MainNav from "../Components/MainNav";

function MainPage() {
  return (
    <Container>
      <MainNav />
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

export default MainPage;
