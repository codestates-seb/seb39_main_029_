import styled from "styled-components";
import "../index";
import Badge from "../Assets/Imgs/badge";
import Locate from "../Assets/Imgs/locate";
import ColorButton from "../Assets/ColorBtn";

function Myinfo() {
  return (
    <Wrapper>
      <UserWrapper>
        <div className="left">
          <div className="img"></div>
          <InfoWrapper>
            <div className="id">Igu</div>
            <div className="reputation">
              <Badge className="badge" />
              800
            </div>
            <div className="intro">hi! master dev</div>
            <div className="location">
              <Locate className="locat" />
              amazon
            </div>
            <div className="summary"></div>
          </InfoWrapper>
        </div>
        <ButtonWrapper>
          <ColorButton mode="GREY" text="Edit Profile" />
          <ColorButton mode="GREY" text="Delete Profile" />
        </ButtonWrapper>
      </UserWrapper>
      <QuesionWrapper>
        <div className="title">Questions</div>
        <div className="posts"></div>
      </QuesionWrapper>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 30px;
  width: 100%;
  font-family: var(--sans-serif);
`;
const UserWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 0 0 30px 0;
  .img {
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
    width: 200px;
    height: 200px;
    margin: 20px;
  }
  .left {
    display: flex;
  }
`;
const InfoWrapper = styled.div`
  padding: 10px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-size: var(--content-font);
  .id {
    font-size: var(--name-font);
    font-weight: bold;
  }
  .reputation {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    .badge {
      fill: var(--gold);
      margin: 0 10px 0 0;
    }
  }
  .intro {
    font-style: italic;
  }
  .location {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    .locat {
      fill: var(--theme-orange);
      margin: 0 10px 0 0;
    }
  }
`;
const ButtonWrapper = styled.div`
  width: 190px;
  padding: 10px;
  margin: 20px;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
`;

const QuesionWrapper = styled.div`
  .title {
    margin: 0 0 20px 0;
    font-size: var(--ad-font);
    font-weight: bold;
  }
  .posts {
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
    width: 100%;
    height: 100%;
  }
`;

export default Myinfo;
