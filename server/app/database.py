from sqlalchemy import (
    create_engine,
    Column,
    Integer,
    String,
    Text,
    Boolean,
    DateTime
)
from sqlalchemy.orm import declarative_base, sessionmaker
from datetime import datetime, timezone

from .config import DATABASE_URL

connect_args = {"check_same_thread": False} if DATABASE_URL.startswith("sqlite") else {}

engine = create_engine(
    DATABASE_URL,
    connect_args=connect_args
)

SessionLocal = sessionmaker(
    autocommit=False,
    autoflush=False,
    bind=engine
)

Base = declarative_base()


def utcnow():
    return datetime.now(timezone.utc)


class Conversation(Base):
    __tablename__ = "conversations"

    id = Column(Integer, primary_key=True)
    title = Column(String(300), default="New Chat")
    pinned = Column(Boolean, default=False)
    created_at = Column(DateTime, default=utcnow)
    updated_at = Column(DateTime, default=utcnow)


class Message(Base):
    __tablename__ = "messages"

    id = Column(Integer, primary_key=True)
    conversation_id = Column(Integer, index=True)
    role = Column(String(30))
    content = Column(Text)
    route = Column(String(100), default="local")
    created_at = Column(DateTime, default=utcnow)


class Memory(Base):
    __tablename__ = "memories"

    id = Column(Integer, primary_key=True)
    memory_key = Column(String(300), unique=True, index=True)
    memory_value = Column(Text)
    created_at = Column(DateTime, default=utcnow)
    updated_at = Column(DateTime, default=utcnow)


class HealthLog(Base):
    __tablename__ = "health_logs"

    id = Column(Integer, primary_key=True)
    component = Column(String(100))
    status = Column(String(50))
    latency_ms = Column(Integer, nullable=True)
    message = Column(Text, nullable=True)
    created_at = Column(DateTime, default=utcnow)


def init_database():
    Base.metadata.create_all(bind=engine)
